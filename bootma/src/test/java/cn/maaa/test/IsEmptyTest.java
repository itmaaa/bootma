package cn.maaa.test;

import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * @author :  mazh
 * @date :  2020/1/19 10:32
 */
public class IsEmptyTest {

    public static void main(String[] args) {
        ArrayList<Student> list = Lists.newArrayList();
        System.out.println(list.isEmpty());
        System.out.println(CollectionUtils.isEmpty(list));

        ArrayList<Student> list1 =  null;
        System.out.println(CollectionUtils.isEmpty(list1));  //   return collection == null || collection.isEmpty();
        System.out.println(list1.isEmpty());

    }


    @Data
    private static class Student{
        private String name;
        private int age;
    }
}
