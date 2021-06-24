package cn.maaa.test.algorithm;

/**
 * 描述信息 字符串转数字
 *
 * @author maaa
 * @date 2021年06月24日 22:34
 */
public class StringToInt {

    public static void main(String[] args) {
        System.out.println(covert("123"));
        System.out.println(covert("-123"));
        System.out.println(covert("7897hggf555"));
        System.out.println(covert("-7897hggf555"));

    }


    public static int covert(String str){
        char[] chars = str.toCharArray();
        int num = 0;
        int temp ;
        boolean fushu = false;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '-'){
                fushu = true;
                continue;
            }
            if(chars[i] < '0' || chars[i] > '9'){
                break;
            }
            temp = chars[i] - 48;
            num = num * 10 + temp;

        }
        return fushu? -num: num;
    }
}
