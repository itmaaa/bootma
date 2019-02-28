package cn.maaa.test;

import cn.maaa.common.utils.BeanConvert;
import cn.maaa.system.domain.Menu;

/**
 * xxx 
 * @author mazh
 * @date 2019年02月28日 14:12 
 */
public class BeanConvertTest {

	public static void main(String[] args) {
		Menu menu = new Menu().setId(1L).setName("哈罗").setUrl("baidu.com");
		System.out.println(menu);
		Menu menu1 = new Menu();
		BeanConvert.execute(menu, menu1);
		System.out.println(menu1);
	}

}
