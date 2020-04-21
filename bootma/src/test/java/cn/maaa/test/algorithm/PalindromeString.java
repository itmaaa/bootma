package cn.maaa.test.algorithm;

import java.util.HashSet;

/**
 *  回文串相关
 * @author :  mazh
 * @date :  2020/4/16 9:54
 */
public class PalindromeString {

    public static void main(String[] args) {
      //  System.out.println(new PalindromeString().test1("abababx"));
       // System.out.println(new PalindromeString().test1("asdfttfddfttf"));

        //System.out.println(new PalindromeString().test2("abccccdd"));

       // System.out.println(new PalindromeString().test3("A man, a plan, a canal: Panama"));
        //System.out.println(new PalindromeString().test3("race a car"));

        System.out.println(test4("BBABCBCAB"));
    }




    /**
     *   Leetcode: LeetCode: 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     * 以某个元素为中心，分别计算偶数长度的回文最大长度和奇数长度的回文最大长度。
     * */
    public String test1(String s) {
        //index表示中心元素，maxLength表示已该元素为中心的最长回文串长度
        int maxLength = 0 , index = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int len =  Math.max(calMaxLength(chars, i,i) - 1, calMaxLength(chars, i,i+1));
            if( maxLength < len){
                maxLength = len;
                index = i;
            }

        }
        int start  = index - (maxLength - 1)/ 2;
        return s.substring(start,start + maxLength );
    }

    public int calMaxLength(char[] chars,int l,int r){
        int length = 0;
        while (l >= 0 && r < chars.length ){
            if(chars[l--] == chars[r++])
                length ++;
            else
                break;
        }
        return length * 2 ;
    }


    /**
     * LeetCode: 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。注 意:假设字符串的长度不会超过 1010。
     *
     * 回文串：“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。——百度百科 地址：https://baike.baidu.com/item/%E5%9B%9E%E6%96%87%E4%B8%B2/1274921?fr=aladdin
     * 示例 1:
     * 输入:
     * "abccccdd"
     * 输出:
     * 7
     *
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * 我们上面已经知道了什么是回文串？现在我们考虑一下可以构成回文串的两种情况：
     *
     * 字符出现次数为双数的组合
     * 字符出现次数为双数的组合+一个只出现一次的字符
     *
     * */

    public int test2(String s) {
        HashSet<Character> hashSet = new HashSet<>(s.length());
        char[] chars = s.toCharArray();
        int count = 0 ;
        for (int i = 0; i < s.length(); i++) {
            if(!hashSet.add(chars[i])){
                count++;
                hashSet.remove(chars[i]);
            }
        }

        return hashSet.isEmpty() ? 2 * count : 2 * count + 1;
    }


    /**
     * 验证回文串
     * LeetCode: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * Copy to clipboardErrorCopied
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     *
     * */

    public boolean test3(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length()-1;
        while (i < j){
            if(!Character.isLetterOrDigit(chars[i]))
                i++;
            else if (!Character.isLetterOrDigit(chars[j]))
                j--;
            else if(Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j]))
                return false;
            else {i++;j--;}

        }
        return true;
    }

    /**
     * 最长回文子序列 的长度
     * 分析
     * 对任意字符串，如果头和尾相同，那么它的最长回文子序列一定是去头去尾之后的部分的最长回文子序列加上头和尾。
     * 如果头和尾不同，那么它的最长回文子序列是去头的部分的最长回文子序列和去尾的部分的最长回文子序列的较长的那一个。
     *
     * str[0...n-1]是给定的字符串序列，长度为n，假设f(0,n-1)表示序列str[0...n-1]的最长回文子序列的长度。
     *
     * 1.如果str的最后一个元素和第一个元素是相同的，则有：f(0,n-1)=f(1,n-2)+2；例如字符串序列“AABACACBA”，第一个元素和最后一个元素相同，其中f(1,n-2)表示红色部分的最长回文子序列的长度；
     *
     * 2.如果str的最后一个元素和第一个元素是不相同的，则有：f(0,n-1)=max(f(1,n-1),f(0,n-2))；例如字符串序列“ABACACB”，其中f(1,n-1)表示去掉第一元素的子序列，f(0,n-2)表示去掉最后一个元素的子序列。
     *
     *  设字符串为s，f(i,j)表示s[i..j]的最长回文子序列。
     *
     * 状态转移方程如下：
     *
     * 当i>j时，f(i,j)=0。
     *
     * 当i=j时，f(i,j)=1。
     *
     * 当i<j并且s[i]=s[j]时，f(i,j)=f(i+1,j-1)+2。
     *
     * 当i<j并且s[i]≠s[j]时，f(i,j)=max( f(i,j-1), f(i+1,j) )。
     *
     * 由于f(i,j)依赖i+1，所以循环计算的时候，第一维必须倒过来计算，从s.length()-1到0。
     *
     * 最后，s的最长回文子序列长度为f(0, s.length()-1)。
     *  以"BBABCBCAB"为例：
     *       i\j  0  1  2  3  4  5  6  7  8
     *        0   1  2  2  3  3  5  5  5  7
     *        1   0  1  1  3  3  3  3  5  7
     *        2   0  0  1  1  1  3  3  5  5
     *        3   0  0  0  1  1  3  3  3  5
     *        4   0  0  0  0  1  1  3  3  3
     *        5   0  0  0  0  0  1  1  1  3
     *        6   0  0  0  0  0  0  1  1  1
     *        7   0  0  0  0  0  0  0  1  1
     *        8   0  0  0  0  0  0  0  0  1
     *
     *   每一个数据需要知道它的左侧，下方，左下方的数据
     *   所以初始数据从 最后一行第一列开始
     * */

    public static int test4(String s) {

        int length = s.length();
        int[][] array = new int[length][length];

        char[] chars = s.toCharArray();

        for (int i = length - 1; i >=0 ; i--) {
            for (int j = 0; j < length; j++) {
                if(i > j)
                    array[i][j] = 0;
                else if(i == j)
                    array[i][j] = 1;
                else {
                    if(chars[i] == chars[j])
                        array[i][j] = array[i+1][j-1] + 2;
                    else
                        array[i][j] = Math.max(array[i+1][j], array[i][j-1]);
                }

            }

        }
        return array[0][length-1];
    }





}
