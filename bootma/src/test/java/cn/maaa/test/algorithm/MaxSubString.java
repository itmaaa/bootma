package cn.maaa.test.algorithm;

/**
 * @author mazh
 * @date 2019年06月28日 17:25 
 */

/**
 * 实现一个函数，求两个字符串中最长子串的长度是多少，
 * 例如“abcaabbccddxxyyzz”和“abcxyyzz”的最长子串的长度是5，实现以下函数原型：
 */

public class MaxSubString {

	static int x=10;
	static  {x+=5;}


	public static void main(String[] args) {
		//输出x是测试选择题的
		//System.out.println("x="+x);

		System.out.println("最长子串长度为："+maxSubstring("abcaabbccddxxyyzz","abcxyyzz"));
	}

	static  {x/=3;}



	/*  动态规划
	*      a b c a a b b c c d d x x y y z z
	*  a   1 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0
	*  b   0 2 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0
	*  c   0 0 3 0 0 0 0 2 1 0 0 0 0 0 0 0 0
	*  x   0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0
	*  y   0 0 0 0 0 0 0 0 0 0 0 0 0 2 1 0 0
	*  y   0 0 0 0 0 0 0 0 0 0 0 0 0 1 3 0 0
	*  z   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 4 1
	*  z   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 5
	*
	* */


	static  int maxSubstring(String str1,String str2){
		int maxLength = 0;
		int length1 = str1.length();
		int length2 = str2.length();
		int arr[][] = new int[length1][length2];
		for (int i = 0; i< length1;i++){
			for (int j = 0; j< length2;j++){
				if(str1.charAt(i) == str2.charAt(j)){
					if( i != 0 && j != 0)
						arr[i][j] = arr[i-1][j-1] + 1;
					else
						arr[i][j] = 1;
					maxLength = arr[i][j] > maxLength ? arr[i][j] : maxLength;
				}else{
					arr[i][j] = 0;
				}
			}
		}
		return maxLength;
	}


}
