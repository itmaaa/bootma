package cn.maaa.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.util.BitSet;
import java.util.Random;

/**
 *  大量整数中去除重复并排序
 * @author Gavenyeah
 * @date Time:
 * @des:
 */
public class BitMap {
    final static int ARRNUM = 800;
    final static int LEN_INT = 32;
    final static int mmax = 9999;
    final static int mmin = 1000;
    final static int N = mmax - mmin + 1;
    public static void main(String args[]) {
        googleBloomFilter();
       /* int[] array = getArray(ARRNUM);
        Arrays.sort(array);
        System.out.println("长度:"+ array.length+",原数组:");
        System.out.println( Arrays.toString(array));
        new BitMap().findDup_jdk(array);
        new BitMap().findDuplicate(array);*/
    }
    public void findDup_jdk(int[] array) {
        System.out.println("*******调用JDK中的库方法--开始********");
        BitSet bitArray = new BitSet(N);

        for (int i = 0; i < ARRNUM; i++) {
            bitArray.set(array[i] - mmin);
        }
        int count = 0;
        for (int j = 0; j < bitArray.length(); j++) {
            if (bitArray.get(j)) {
                System.out.print(j + mmin + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("排序后的数组大小为：" + count );
    }
    public void findDuplicate(int[] array) {
        System.out.println("*******调用自己的位运算方法--结束********");
        int[] bitArray = setBit(array);
        printBitArray(bitArray);
    }

    public static void googleBloomFilter(){
        System.out.println("*******调用googleBloomFilter--结束********");
        // 创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }

    public int[] setBit(int[] array) {// 首先取得数组位置下标 i/32, 然后 或上
        // 在该位置int类型数值的bit位：i % 32
        int m = array.length;
        int bit_arr_len = N / LEN_INT + 1;
        int[] bitArray = new int[bit_arr_len];
        for (int i = 0; i < m; i++) {
            int num = array[i] - mmin;
            //任何数和0 或都为它本身  和1或都为1
            bitArray[num / LEN_INT] |= (1 << (num % LEN_INT));
        }
        return bitArray;
    }

    public int getBit(int[] bitArray, int k) {// 1右移 k % 32位 与上 数组下标为 k/32 位置的值
        return bitArray[k / LEN_INT] & (1 << (k % LEN_INT));  //任何数和1 与都为它本身  和0与都为0
    }


    public void printBitArray(int[] bitArray) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getBit(bitArray, i) != 0) {
                count++;
                System.out.print(i + mmin + " ");
            }
        }
        System.out.println();
        System.out.println("去重排序后的数组大小为：" + count);
    }


    //1000-9999
    public static int[] getArray(int ARRNUM) {
        int array[] = new int[ARRNUM];
        Random r = new Random();
        for (int i = 0; i < ARRNUM; i++) {
            array[i] = r.nextInt(N) + mmin;
        }
        return array;
    }
}
