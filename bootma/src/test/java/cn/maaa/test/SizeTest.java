package cn.maaa.test;

/**
 * Description: 大于参数n的最小2的n次方取值，比如17取值32
 *
 * @author mazh
 * @date 2021/3/25 17:22
 */
public class SizeTest {

    public static void main(String[] args) {
        System.out.println(tableSizeFor(16));
        //System.out.println((int) Math.ceil(BigDecimal.valueOf(2009).divide(BigDecimal.valueOf(2000)).doubleValue()));
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
}
