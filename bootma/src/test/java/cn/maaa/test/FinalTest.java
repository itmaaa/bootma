package cn.maaa.test;

/**
 * @author :  mazh
 * @date :  2020/1/16 13:45
 */
public class FinalTest {
    public static void main(String[] args) {
        Son son = new Son();
        son.askFatherToDo();
    }
}

class Father{

     final  void doSomeThing(){
        System.out.println("Father doSomeThing");
    }
}

class Son extends Father{
    public void askFatherToDo(){
        super.doSomeThing();
    }

}