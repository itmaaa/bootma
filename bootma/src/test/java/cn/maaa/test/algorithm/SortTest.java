package cn.maaa.test.algorithm;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2021年07月07日 20:32
 */
public class SortTest {


    public static void main(String[] args) {
        int[] array = new int[]{5,1,6,8,91,19};

        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }
}
