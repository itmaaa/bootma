package cn.maaa.common.utils;

import cn.maaa.system.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


/**
 * bean转化类
 * @author mazh
 * @date 2018年12月13日 9:22 
 */
@Component
public class BeanConvert {

	public static <T> T execute(Object source, T target) {
		BeanUtils.copyProperties(source,target);
		return target;
	}

	/**
	 * apache BeanUtils性能较差,不推荐使用
	 * 能赋值同属性名不同类型的参数,spring的beanUtils则不行
	 */
	@Deprecated
	public static <T> T apacheExecute(Object source, T target) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(target,source );
			return target;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public  Object exec(Object source, Object target) {
		return execute(source,target);
	}

	//aop只能拦截非静态方法,同时方法所属的类需交由spring管理
	public void sayHello(){
		System.out.println("Hello,it's me!");
	}

}



