package cn.maaa.test.algorithm;

import java.util.Stack;

/**
 * 栈相关
 * @author :  mazh
 * @date :  2020/4/21 9:44
 */
public class StackTest {

    public static void main(String[] args) {

       /* StackToQueue stackToQueue = new StackToQueue();
        stackToQueue.push(1);
        stackToQueue.push(2);
        stackToQueue.push(3);
        stackToQueue.push(4);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        stackToQueue.push(5);
        stackToQueue.push(6);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());*/

        System.out.println(isPopOrder(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
        System.out.println(isPopOrder(new int[]{1,2,3,4,5},new int[]{4,3,5,1,2}));
    }


    /**
     * 栈的压入,弹出序列
     * 题目描述：
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等!!
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * 题目分析：
     * 这道题想了半天没有思路，参考了Alias的答案，他的思路写的也很详细应该很容易看懂。 作者：Alias https://www.nowcoder.com/questionTerminal/d77d11405cc7470d82554cb392585106 来源：牛客网
     *
     * 【思路】借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是4，很显然1≠4，
     *  所以我们继续压栈，直到相等以后开始出栈，出栈一个元素，则将出栈顺序向后移动一位，直到不相等继续压栈，这样循环等压栈顺序遍历完成，如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
     *
     * 举例：
     *
     * 入栈1,2,3,4,5
     *
     * 出栈4,5,3,2,1
     *
     * 首先1入辅助栈，此时栈顶1≠4，继续入栈2
     *
     * 此时栈顶2≠4，继续入栈3
     *
     * 此时栈顶3≠4，继续入栈4
     *
     * 此时栈顶4＝4，出栈4，弹出序列向后一位，此时为5，,辅助栈里面是1,2,3
     *
     * 此时栈顶3≠5，继续入栈5
     *
     * 此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
     *
     * …. 依次执行，最后辅助栈为空。如果不为空说明弹出序列不是该栈的弹出顺序。
     *
     * 考察内容：
     * 栈
     * */
    public static boolean isPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        for (int pushNum : pushA) {
            stack.push(pushNum);
            while (!stack.isEmpty() && stack.peek() == popA[index]){
                stack.pop();
                index ++;
            }
        }

        return  stack.isEmpty();
    }





    /**
     * 题目描述：
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *
     * 问题分析：
     * 先来回顾一下栈和队列的基本特点： 栈：后进先出（LIFO）
     * 队列： 先进先出 很明显我们需要根据JDK给我们提供的栈的一些基本方法来实现。先来看一下Stack类的一些基本方法：
     *
     * 既然题目给了我们两个栈，我们可以这样考虑当push的时候将元素push进stack1，pop的时候我们先把stack1的元素pop到stack2，
     * 然后再对stack2执行pop操作，这样就可以保证是先进先出的。（负[pop]负[pop]得正[先进先出]）
     * */

    static class   StackToQueue{
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(Integer number){
            stack1.push(number);
        }

        public Integer pop(){
            //如果两个队列都为空则抛出异常,说明用户没有push进任何元素
            if(stack1.empty()&&stack2.empty()){
                throw new RuntimeException("Queue is empty!");
            }

            if(stack2.isEmpty()){
                while (!stack1.isEmpty())
                    stack2.push(stack1.pop());
            }

             return stack2.pop();
        }
    }


}
