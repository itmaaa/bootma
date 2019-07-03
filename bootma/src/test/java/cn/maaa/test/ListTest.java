package cn.maaa.test;

import java.util.*;

/**
 * List集合取交集、并集、去除重复数据等
 * @author mazh
 * @date 2019年07月02日 14:52 
 */
public class ListTest {

	public static void main(String[] args) {
		List<String> listA = new ArrayList<>();
		List<String> listB = new ArrayList<>();

		//取交集
		init(listA,listB );
		boolean b = listA.retainAll(listB);

		System.out.println( b);
		System.out.println(listA);

		//取并集
		init(listA,listB );
		listA.removeAll(listB);
		listA.addAll(listB);
		System.out.println(listA);

		//取差集
		init(listA,listB );
		listA.removeAll(listB);
		System.out.println(listA);

		Set<Object> set = new HashSet<Object>();
		set.add("maz");

		init(listA,listB );
		Collections.sort(listA);
		System.out.println(listA);
	}

	public static void init(List<String> listA,List<String> listB){
		listA.clear();
			listA.add("B");
			listA.add("D");
			listA.add("A");
		listB.clear();
			listB.add("B");
			listB.add("C");
			listB.add("D");
	}



}
