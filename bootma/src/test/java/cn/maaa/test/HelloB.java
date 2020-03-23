package cn.maaa.test;

/**
 * @author :  mazh
 * @date :  2020/3/23 17:15
 *
 *  解析:
 *  静态代码块:类的加载而执行，只执行一次
 *  代码块:对象一实例化就执行，每实例化一次执行一次
 *
 *  无继承的的初始化顺序：
 *      静态代码块 > 代码块 > 构造函数
 *   有继承的初始化顺序：
 *      父类静态代码块 > 子类静态代码块 > 父类代码块 > 父类构造函数 > 子类代码块 > 子类构造函数
 */

class HelloA{
    public HelloA(){
        System.out.println("HelloA");
    }
    {
        System.out.println("I'm A class");
     }
    static {
        System.out.println("static A");
    }
}

public class HelloB extends HelloA{
    public HelloB(){
        System.out.println("HelloB");
    }
    {
        System.out.println("I'm B class");
    }
    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB();
    }
}

/*      static A
        static B
        I'm A class
        HelloA
        I'm B class
        HelloB
*/