package cn.maaa.ztest.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
		/** 查询 */
		@GetMapping("/test")
		public String testFreeMarker(Model model){
			/** 添加响应数据 */
			model.addAttribute("tip", "测试数据更新了...");


			/*try {
				int i =1/0;
			} catch (Exception e) {
				throw  new RuntimeException("testFreeMarker执行异常...");
			}*/
			System.out.println("testFreeMarker执行完");

			/** 返回视图 */
			return "test";
		}
		
		@Autowired
		private Environment env;
		
		@Value("${server.port}")
		private String port;
		
		/*@Value("${server.context-path}")
		private String path;*/
		
		
		@ResponseBody
		@GetMapping("/hello")
		public String hello(){
			System.out.println(env.getProperty("server.port"));
			System.out.println(env.getProperty("server.context-path"));
			
			System.out.println("@value-port="+port);
			//System.out.println("@value-context-path="+path);
			return "Hello World";
		}


	@Autowired
	private TestService testService;

	@ResponseBody
	@GetMapping("/methodOne")
	public String methodOne(){
		testService.methodOne();
		return "methodOne";
	}

	@ResponseBody
	@GetMapping("/methodTwo")
	public String methodTwo(){
		testService.methodTwo();
		return "methodTwo";
	}


}
