package cn.maaa.test;

import java.util.HashSet;

/**
 * 描述信息
 *
 * 示例1：
 * s = 'abcabcbb' 最长的无重复子串为'abc'，长度为3
 * 示例2:
 * s = 'aabbccdd' 最长的无重复子串为'ab'，长度为2
 * 示例3:
 * s = 'abccbae' 最长无重复子串为'cbae'，长度为4
 * 示例4:
 * s = 'bbbbb' 最长的无重复子串为'b'，长度为1
 *
 * @author maaa
 * @date 2021年07月27日 19:40
 */
public class SubStrTest {

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        System.out.println("maxlength:"+ maxLength(str1));
        String str2 = "aabbccdd";
        System.out.println("maxlength:"+ maxLength(str2));
        String str3 = "abccbae";
        System.out.println("maxlength:"+ maxLength(str3));
        String str4 = "bbbbb";
        System.out.println("maxlength:"+ maxLength(str4));
    }

    public static int maxLength(String str){
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = str.toCharArray();
        int max = 0,temp =0;
        for (int i = 0; i < chars.length; i++) {
            boolean add = hashSet.add(chars[i]);
            if(add){
                temp++;
            }else{
                temp = 0;
                hashSet.clear();
                i--;   //"dvdf" 不符合
            }
            max = Math.max(max,temp);
        }
        return max;
    }
}
