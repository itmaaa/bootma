package cn.maaa.test;

/**
 * 最长回文子串
 * @author :  mazh
 * @date :  2020/4/16 9:54
 */
public class PalindromeString {
    private int index, len;

    public static void main(String[] args) {
       // System.out.println(new PalindromeString().longestPalindrome("babad"));
       // System.out.println(new PalindromeString().longestPalindrome("cbbd"));
        System.out.println(new PalindromeString().longestPalindrome("asdfttfddfttf"));
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        for (int i = 0; i < s.length() - 1; i++) {
            //查询基数回文串   aba
           // PalindromeHelper(s, i, i);
            //查询偶数回文串   abba
            PalindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }

    public void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }
}
