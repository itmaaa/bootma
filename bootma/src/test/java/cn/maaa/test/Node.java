package cn.maaa.test;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :  mazh
 * @date :  2020/4/22 15:06
 */
@Data
@Accessors(chain = true)
public  class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}
