package cn.maaa.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 异常工具类
 * @author lijian
 * @date 2018年09月15日 17:13
 */
public class ExceptionUtils {

	/**
	 * 获取异常信息及异常类
	 * @param e
	 * @return
	 */
	public static String errorMsg(Exception e){
		//e.printStackTrace();
		return StringUtils.joinWith(" - ",e.getMessage(), e.getClass());
	}
}
