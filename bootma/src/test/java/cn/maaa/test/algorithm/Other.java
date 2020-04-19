package cn.maaa.test.algorithm;

import java.util.Scanner;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2020年04月19日 12:07
 */
public class Other {

    public static void main(String[] args) {
        System.out.println(new Other().cal1());
    }


    /**
     * 括号匹配深度
     *
     * 输入描述:
     * 输入包括一个合法的括号序列s,s长度length(2 ≤ length ≤ 50),序列中只包含'('和')'。
     *
     * 输出描述:
     * 输出一个正整数,即这个序列的深度。
     * Copy to clipboardErrorCopied
     * 示例：
     *
     * 输入:
     * (())
     * 输出:
     * 2
     *
     * */
    public int cal1(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int length = 0, max = 0;

        char[] chars = line.toCharArray();
        for (char aChar : chars) {
            if(aChar == '(')
                length++;
            else
                length--;
            max = Math.max(max,length);
        }
        scanner.close();
        return max  ;
    }
}
