package cn.maaa.test;

import lombok.Data;

/**
 * 单链表反转
 * 为了方便理解，我们以 1->2->3->4这个链表来做演示。输出的效果是4->3->2->1
 * @author mazh
 * @date 2019年07月15日 14:11 
 */
public class SinglyLinkedList {

	public static void main(String[] args) {

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
	 * 遍历反转
	 */
	public static Node reverse1(Node head) {
		Node pre = null;
		Node next;

		while (head != null ){
			next = head.next;
			head.next = pre;
           	pre = head ;
           	head = next;
		}

		return pre;
	}

	/**
	 * 递归反转
	 */
	public static Node reverse2(Node head) {
        if(head == null || head.next == null)
		       return head;

        Node temp = head.next;

		Node newHead = reverse2(temp);

		temp.next = head;

		head.next = null;

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
	public static class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
		}
	}

}


