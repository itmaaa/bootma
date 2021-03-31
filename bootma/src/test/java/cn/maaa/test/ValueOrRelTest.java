package cn.maaa.test;

import cn.maaa.system.domain.Dept;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;

/**
 * Description: 值传递测试
 *
 * @author mazh
 * @date 2021/3/31 11:16
 */
public class ValueOrRelTest {

    @Test
    public  void test1() {
        Dept dept = new Dept();
        dept.setName("开发部");
        change1(dept);
        System.out.println("dept:"+dept);    //开发部
    }


    private void change1(Dept dept){
        Dept dept1 = new Dept();
        dept1.setName("行政部");
        dept = dept1;  //此时dept指向新的dept1对象
        dept.setName("销售部");  //改变的是dept对象的值
        System.out.println("dept1:"+dept1);
    }

    @Test
    public  void test2() {
        ArrayList<String> list = Lists.newArrayList();
        list.add("冰淇淋");
        change2(list);
        System.out.println(list);    //冰淇淋
    }


    private void change2(List list){
        ArrayList<String> list1 = Lists.newArrayList();
        list1.add("啤酒鸭");
        list = list1;
    }

    @Test
    public  void test3() {
        Integer number = 10000;
        change3(number);
        System.out.println(number);    //10000
    }


    private void change3(Integer number){
        number = 10086;
    }

    @Test
    public  void test4() {
        Integer number = 10000;
        change4(number);
        System.out.println(number);    //10000
    }


    private void change4(Integer number){
        ++number;
    }
}
