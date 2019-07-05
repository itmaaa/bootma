package cn.maaa.common.logback.thread;


import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * TrackExecutorService
 * @author mazh
 * @date 2019年06月18日 9:47
 */
public  class TrackExecutorService{

	private final static String EXECUTE_METHOD_NAME = "execute";

	private final static String SUBMIT_METHOD_NAME = "submit";

	private  ExecutorService executorService;

	private TrackExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public static ExecutorService wrapper(ExecutorService executorService){
		TrackExecutorService instance = new TrackExecutorService(executorService);
		return  instance.getProxyInstance();
	}

	//接口，newProxyInstance第二个参数是new Class[]{ExecutorService.class}
	public ExecutorService getProxyInstance(){
		return (ExecutorService)Proxy.newProxyInstance(
				executorService.getClass().getClassLoader(),
				new Class[]{ExecutorService.class}, (proxy, method, args) -> {

					// 执行execute方法时包装runnable
					if(method.getName().equals(EXECUTE_METHOD_NAME)){
						Runnable runnable = (Runnable)args[0];
						args[0] = new RunnableWrapper() {
							@Override
							public void trackRun() {
								runnable.run();
							}
						};
					}

					// 执行submit方法时包装callable
					if(method.getName().equals(SUBMIT_METHOD_NAME)){
						Object arg = args[0];
						if(arg instanceof Callable){
							Callable callable = (Callable)arg;
							args[0] = new CallableWrapper<Object>() {
								@Override
								public Object trackCall() throws Exception{
									return callable.call();
								}};
						}
					}
					Object returnValue = method.invoke(executorService, args);
					return returnValue;
				});
	}
}
