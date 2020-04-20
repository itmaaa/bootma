package cn.maaa.test.algorithm;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 链表相关
 *
 * 单链表只有一个指向下一结点的指针，也就是只能next
 * 双链表除了有一个指向下一结点的指针外，还有一个指向前一结点的指针，可以通过prev()快速找到前一结点，顾名思义，单链表只能单向读取
 *
 * @author mazh
 * @date 2019年07月15日 14:11 
 */
public class SinglyLinkedList {

	public static void main(String[] args) {
		//reverse();
		Node node1 = initNode("246");
		Node node2 = initNode("564999");
		print(linkAdd(node1,node2));
	}




	/**
	 * 题目描述
	 * Leetcode:给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
	 *
	 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
	 *
	 * 示例：
	 *
	 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * 输出：7 -> 0 -> 8
	 * 原因：342 + 465 = 807
	 *
	 * */

	//解法一   不简洁
	public static Node linkAdd(Node node1,Node node2){

		if(node1 == null)
			return node2;
		if(node2 == null)
			return node1;

		//链表补齐等长
		Node temp1 = node1, temp2 = node2;

		while(temp1 != null || temp2 != null){
			if(temp1.next == null && temp2.next == null)
			     break;
			else if(temp1.next == null){
				temp1.next = new Node(0);
			}else if(temp2.next == null){
				temp2.next = new Node(0);
			}

			temp1 = temp1.next;
			temp2 = temp2.next;

		}
		//标志是否进位
		boolean flag = false;
		//结果  哑节点
		Node result =  new Node(0);
		Node  curr = result ;

		while (node1 != null && node2 != null){
			    //if()
				int sum = node1.getData() + node2.getData();
				if(flag)sum++;

			    int number  = sum % 10;
				flag = sum / 10 == 1 ;

				Node node = new Node(number);

				curr.setNext(node);
			    curr = node;

				node1 = node1.next;
				node2 = node2.next;

		}
		if(flag)
			curr.next = new Node(1);

		return result.next;

	}

	//解法二   简洁
	public static Node linkAdd1(Node node1,Node node2){
		Node dummyHead = new Node(0);
		Node p = node1, q = node2, curr = dummyHead;
		//carry 表示进位数
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.getData() : 0;
			int y = (q != null) ? q.getData() : 0;
			int sum = carry + x + y;
			//进位数
			carry = sum / 10;
			//新节点的数值为sum % 10
			curr.next = new Node(sum % 10);
			curr = curr.next;
			if (p != null) p = p.next;
			if (q != null) q = q.next;
		}
		if (carry > 0) {
			curr.next = new Node(carry);
		}
		return dummyHead.next;
	}

	/**
	 * 单链表反转
	 * 为了方便理解，我们以 1->2->3->4这个链表来做演示。输出的效果是4->3->2->1
	 * */
	public static void reverse(){
		Node head = init();

		System.out.print("原链表:");
		print(head);


		head = reverse1(head);

		System.out.print("遍历翻转后:");

		print(head);

		head = reverse2(head);

		System.out.print("再递归翻转后:");

		print(head);
	}



	/**
	 *  1->2->3->4
	 *  1  2->1   3->2->1   4->3->2->1
	 * 遍历反转
	 */
	public static Node reverse1(Node head) {

		Node next = null;
		Node newHead = null;

		while (head != null){
			next = head.next;
			head.next = newHead;
			newHead = head;
			head  = next;
		}
		return newHead;
	}

	/**
	 *  1->2->3->4
	 *  4  4->3   4->3->2   4->3->2->1
	 *
	 *   1->2->3->4 反转  等于
	 *   (2->3->4) 反转再 -> 1  等于
	 *   (3->4) 反转再 -> 2 -> 1
	 * 递归反转
	 */
	public static Node reverse2(Node head) {

      if(head.next == null)
      	return head;

      Node temp = head.next;
	  Node newHead = reverse2(temp);

	  temp.next = head ;
	  head.next = null;

	  //返回的节点始终是最后的节点，即新的头节点，递归只是更改节点的内指向
      return newHead;
	}

	/**
	 * 初始链表
	 */
	public static Node init(){
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);

		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);

		return node1;
	}

	/**
	 *  字符串转链表
	 *  234   2 -> 3 -> 4
	 * */
	public static Node initNode(String number){
		char[] chars = number.toCharArray();
		Node temp = new Node(0);
		Node data = temp;

		for (char aChar : chars) {
			// char ‘3’ 转为 int 3, 不能直接转化，那样得到是‘3’的Ascii
			Node node = new Node(aChar - '0');
			temp.setNext(node);
			temp = node;
		}
		return data.next;
	}

	/**
	 * 打印
	 */
	public static void print(Node head){
		Node h = head;
		while (h != null){
			System.out.print(h.getData());
			if (h.next == null)
				System.out.println();
			else
				System.out.print("->");
			h = h.next;
		}
	}

	@Data
	@Accessors(chain = true)
	public static class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
		}
	}

}


