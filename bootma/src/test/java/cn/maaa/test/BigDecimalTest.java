package cn.maaa.test;

import java.math.BigDecimal;

/**
 * BigDecimal 主要用来操作（大）浮点数，BigInteger 主要用来操作大整数（超过 long 类型）。
 * BigDecimal 的实现利用到了 BigInteger, 所不同的是 BigDecimal 加入了小数位的概念
 * @author :  mazh
 * @date :  2020/1/15 13:55
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        /**
         * 《阿里巴巴Java开发手册》中提到：
         * 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断。
         * 具体原理和浮点数的编码方式有关，这里就不多提了，我们下面直接上实例：
         * */
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false


        /**
         * 禁止如下: 使用构造方法 BigDecimal(double) 的方式把 double值 转化为 BigDecimal对象
         * 说明:BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
         * 如:BigDecimal g = new BigDecimal(0.1f); 实际的存储值 为:0.100000001490116119384765625
         * 优先推荐入参为String的构造方法，或使用BigDecimal的valueOf⽅方法，此方法内部其实执行了Double的 toString，而Double的toString按double的实际能表达的精度对尾数进行了截断
         *
         * */
        BigDecimal bigDecimal3 = new BigDecimal(0.1f);
        System.out.println(bigDecimal3);


        /**
         * 正确用法
         * */
        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        System.out.println(bigDecimal1);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(0.1);
        System.out.println(bigDecimal2);
    }
}
