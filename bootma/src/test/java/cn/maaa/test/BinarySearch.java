package cn.maaa.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 二分查找,又称折半查找
 * 使用条件：查找序列是顺序结构，有序
 * @author mazh
 * @date 2019年07月17日 17:33 
 */
@Slf4j
public class BinarySearch {

	public static void main(String[] args) {

		int[] arr ={1,4,7,9,13,25,46,58,79,81,91,123,166,182,211,321,354,621,841,985};
		int station = search1(arr, 123, 0, arr.length-1);
		log.info("数组下标:{},值:{}", station,station == -1 ? "不存在" : arr[station]);

		station = search2(arr, 123);
		log.info("数组下标:{},值:{}", station,station == -1 ? "不存在" : arr[station]);

	}

	/**
	 * 递归写法
	 * 时间复杂度O(log2 N),最好情况下为O（1）
	 * 递归的次数和深度都是log2 N,每次所需要的辅助空间都是常数级别的：
	 *  空间复杂度：O(log2N )
	 */
	public static int search1(int[] arr,int key,int start,int end){
		if(key < arr[start] || key > arr[end] || start > end)
			return -1;

		int middle = (start + end) >>> 1;

		if(arr[middle] > key)
			end = middle -1;
		else if(arr[middle] < key)
			start = middle + 1;
		else
			return middle;

		return search1(arr, key, start, end);
	}

	/**
	 * while循环
	 * 时间复杂度O(log2 N),最好情况下为O（1）
	 * 由于辅助空间是常数级别的所以：
	 * 空间复杂度是O(1);
	 */
	public static int search2(int[] arr,int key){
        int start = 0;
        int end = arr.length-1;

		if(key < arr[start] || key > arr[end])
			return -1;

        while (start <= end){
			int middle = (start + end) >>> 1;

			if(arr[middle] > key)
				end = middle -1;
			else if(arr[middle] < key)
				start = middle + 1;
			else
				return middle;
		}

		return -1;
	}
}
