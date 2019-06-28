package cn.maaa.test;

/**
 * @author mazh
 * @date 2019年06月28日 17:25 
 */

/**
 * 实现一个函数，求两个字符串中最长子串的长度是多少，
 * 例如“abcaabbccddxxyyzz”和“abcxyyzz”的最长子串的长度是5，实现以下函数原型：
 */
public class MaxSubString {

	public static void main(String[] args) {
		System.out.println(maxSubstring("abcaabbccddxxyyzz","abcxyyzz"));
	}


	static  int maxSubstring(String str1,String str2){

		int Str1length = str1.length();
		int Str2length = str2.length();
		int large;
		int small;
		String largeStr;
		String smallStr;

		if(Str1length > Str2length){
			large = Str1length;
			small = Str2length;
			largeStr = str1;
			smallStr = str2;
		}else{
			large = Str2length;
			small = Str1length;
			largeStr = str2;
			smallStr = str1;
		}

		int maxSubLength = 0 ;


		int k = 0;

		for (int i=0;i<large;i++){

			char letter1 = largeStr.charAt(i);

			for (int j = k;j<small;j++){
				char letter2 = smallStr.charAt(j);

				if(letter1 == letter2){
					k++;
					break;
				}else {
					if(k > maxSubLength)
						maxSubLength = k;
					if(k != 0){
						k = 0;
						j = 0;
					}
				}

			}

		}

		return maxSubLength;
	}
}
