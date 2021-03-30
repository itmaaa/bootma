package cn.maaa.test;

import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/3/24 10:56
 */
public class ProxyTest {


    public static void main(String[] args) {
        Cat target = new Cat();
        Animal proxy = (Animal) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                 (proxy1, method, args1) -> {
                    System.out.println("start");
                    Object invoke = method.invoke(target, args1);
                     System.out.println(invoke);
                    System.out.println("end");
                    return invoke;
                });
        proxy.eat();
    }


}

interface Animal{
    String eat();
}

class Cat implements Animal{

    @Override
    public String eat() {
        return "fish";
    }
}
