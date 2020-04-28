package cn.maaa.test.algorithm;

import java.util.Stack;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2020年04月25日 14:10
 */
public class MinStack {

    private Stack stack;
    private int  min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if(x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if((int)stack.pop() == min){
            min = (int) stack.pop();
        }
    }

    public int top() {
          return (int) stack.peek();
    }

    public int getMin() {
         return min;
    }
}
