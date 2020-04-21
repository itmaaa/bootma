package cn.maaa.test.algorithm;

import java.util.Scanner;

/**
 * 其他算法题
 *
 * @author maaa
 * @date 2020年04月19日 12:07
 */
public class Other {

    public static void main(String[] args) {
       // System.out.println(new Other().cal1());
        //System.out.println(new Other().test(16));
        adjust(new int[]{1,2,6,7,5,4,23,8,9});
    }


    /**
     * 调整数组顺序使奇数位于偶数前面
     *
     * 题目描述：
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * 问题解析：
     * 1 .这道题有挺多种解法的，给大家介绍一种我觉得挺好理解的方法： 我们首先统计奇数的个数假设为n,然后新建一个等长数组，然后通过循环判断原数组中的元素为偶数还是奇数。
     * 如果是则从数组下标0的元素开始，把该奇数添加到新数组；如果是偶数则从数组下标为n的元素开始把该偶数添加到新数组中。
     *
     * 2 不创建新数组  偶数放最后一位，后面的元素向前移动
     *
     * */

    public static  void adjust(int[] array){
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if(array[i] % 2 == 1)
                continue;
            else {
                int index = i;
                int temp = array[i];
                while (i< array.length - 1)
                    array[i] = array[++i];
                array[i] = temp;
                len--;
                i = index - 1;
            }
        }

        for (int i : array) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 二维数组查找
     * 题目描述：
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 问题解析：
     * 这一道题还是比较简单的，我们需要考虑的是如何做，效率最快。这里有一种很好理解的思路：
     *
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
     *
     *
     *    2  5  8  11
     *    3  6  9  14
     *    7  8  10 16
     *    10 12 13 18
     * */
    public boolean test(int number){

        int[][] array = {{2 , 5 , 8  ,11},{3,  6 , 9 , 14},{7 , 8 , 10 ,16},{10 ,12 ,13, 18}};
        int start = array.length - 1;

        for (int i = 0; i < array[start].length; i++) {
            if(array[start][i] == number){
                System.out.println("纵坐标:"+ start + ",横坐标:"+i);
                return true;
            } else if(array[start][i] < number)
                continue;
            else {
                if(start > 0){
                    start --; i--;
                }
                else break;
            }
        }

        return  false;
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
