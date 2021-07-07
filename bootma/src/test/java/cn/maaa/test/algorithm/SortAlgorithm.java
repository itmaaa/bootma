package cn.maaa.test.algorithm;

import com.google.common.primitives.Ints;

import java.util.List;

public class SortAlgorithm {

    public static void main(String[] args) {
        int[] numbers = {16,5,14,2,33,24,43,3,28,17,66};
        //bubbleSort(numbers);
        //selectionSort(numbers);
        //insertionSort(numbers);
        fastSort(numbers,0,numbers.length-1);
        List<Integer> list = Ints.asList(numbers);
        System.out.println(list);
    }

    //插入排序
    public static void insertionSort(int[] numbers){
        for (int i = 1; i < numbers.length; i++) {
            int temp = numbers[i] ;
            for (int j = i -1; j >= 0 ;j--){
                if(temp < numbers[j]){
                    numbers[j+1] = numbers[j];
                    if(j == 0)
                        numbers[j] = temp;
                }else {
                    numbers[j+1] = temp;
                    break;
                }
            }
        }
        List<Integer> list = Ints.asList(numbers);
        System.out.println(list);
    }

    //选择排序
    /**
     * 在一个长度为 N 的无序数组中，第一次遍历 n-1 个数找到最小的和第一个数交换。
     * 第二次从下一个数开始遍历 n-2 个数，找到最小的数和第二个数交换。
     * 重复以上操作直到第 n-1 次遍历最小的数和第 n-1 个数交换，排序完成。
     * */
    public static void selectionSort(int[] numbers){
        for (int n = 0; n < numbers.length; n++) {
            int minIndex = n;
            int temp ;
            for (int i = n + 1; i < numbers.length; i++) {
                if( numbers[i] < numbers[minIndex] ){
                    minIndex = i;
                }
            }
            if(minIndex == n) continue;
            temp = numbers[n];
            numbers[n] = numbers[minIndex];
            numbers[minIndex] = temp;
        }
        List<Integer> list = Ints.asList(numbers);
        System.out.println(list);
    }


    //冒泡排序
    /**
     * 比较相邻两个数据如果。第一个比第二个大，就交换两个数
     * 对每一个相邻的数做同样1的工作，这样从开始一队到结尾一队在最后的数就是最大的数。
     * 针对所有元素上面的操作，除了最后一个。
     * 重复1~3步骤，知道顺序完成。
     *
     * */
    public static void bubbleSort(int[] numbers){
        int k = numbers.length ;
        for (int n = 0; n < numbers.length; n++) {
            k--;
            for (int i = 0; i < k; i++) {
                if(numbers[i] > numbers[i+1] ){
                    int temp =  numbers[i+1];
                    numbers[i+1] = numbers[i];
                    numbers[i] = temp;
                }
            }

        }
        List<Integer> list = Ints.asList(numbers);
        System.out.println(list);
    }


    public static void fastSort(int[] numbers,int l,int r){
        if(l >= r)
            return;

        int left = l,right = r;
        int pivot = numbers[left];

        while (left < right){
            while (left< right && numbers[right] >= pivot){
                right--;
            }
            if(left < right){
                numbers[left] = numbers[right];
            }
            while (left< right && numbers[left] <= pivot){
                left++;
            }
            if(left < right){
                numbers[right] = numbers[left];
            }
        }
        numbers[left] = pivot;
        fastSort(numbers,l,left-1);
        fastSort(numbers,right+1,r);

    }

}


