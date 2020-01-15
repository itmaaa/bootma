package cn.maaa.test;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.T;

/**
 * @author :  mazh
 * @date :  2020/1/15 15:09
 */
public class ArrayAsListTest {

    public static void main(String[] args) {
        //String[] myArray = { "Apple", "Banana", "Orange" };
       // List<String> myList1  = Arrays.asList(myArray);
        //上面两个语句等价于下面一条语句
        //List<String> myList2 = Arrays.asList("Apple","Banana", "Orange");

        //Arrays.asList()将数组转换为集合后,底层其实还是数组

       // myList1.add("pear");  //UnsupportedOperationException
       // myArray[0] = "pear";
       // System.out.println(myList1); //[pear, Banana, Orange]

       /*
       * 传递的数组必须是对象数组，而不是基本类型。
         Arrays.asList()是泛型方法，传入的对象必须是对象数组。
       * */
     /*   int[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList);   //[[I@5387f9e0]
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int [] array=(int[]) myList.get(0);
        System.out.println(array[0]);//1*/

        /*
        * 当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身！
        * 此时List 的唯一元素就是这个数组，这也就解释了上面的代码。
            我们使用包装类型数组就可以解决这个问题。
        * */

      /*  Integer[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList);   //[1, 2, 3]*/


       // List myList = Arrays.asList(1, 2, 3);
      //  myList.add(4);//运行时报错：UnsupportedOperationException
       // myList.remove(1);//运行时报错：UnsupportedOperationException
       // myList.clear();//运行时报错：UnsupportedOperationException
       // Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,这个内部类并没有实现集合的修改方法或者说并没有重写这些方法。

       // List myList = Arrays.asList(1, 2, 3);
       // System.out.println(myList.getClass());//class java.util.Arrays$ArrayList

        // 数组转成list的正确姿势

      //  1. 自己动手实现（教育目的）

       // Integer [] myArray = { 1, 2, 3 };
        //System.out.println(arrayToList(myArray).getClass());//class java.util.ArrayList
        //2. 最简便的方法(推荐)

       // List list = new ArrayList<>(Arrays.asList("a", "b", "c"));

       // 3. 使用 Java8 的Stream(推荐)

      /*  Integer [] myArray = { 1, 2, 3 };
        List myList = Arrays.stream(myArray).collect(Collectors.toList());

        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List myList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());*/


       /*    Collection.toArray()方法使用的坑&如何反转数组
           该方法是一个泛型方法：<T> T[] toArray(T[] a); 如果toArray方法中没有传递任何参数的话返回的是Object类型数组。
        由于JVM优化，new String[0]作为Collection.toArray()方法的参数现在使用更好，
        new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型
    */

       /* String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s = list.toArray(new String[0]);//没有指定类型的话会报错
        System.out.println(s);
        Object[] objects = list.toArray();
        System.out.println(objects);*/
    }

    //JDK1.5+
    static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s : array) {
            l.add(s);
        }
        return (l);
    }




}
