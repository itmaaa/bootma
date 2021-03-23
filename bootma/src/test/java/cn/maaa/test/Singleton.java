package cn.maaa.test;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/3/23 16:22
 */
public enum Singleton {
    INSTANCE;
    Singleton() {
        // Initialization configuration which involves
        // overriding defaults like delivery strategy
    }


    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Singleton.getInstance().sayHello();
    }
}
