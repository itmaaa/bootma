package cn.maaa.test.algorithm;

import com.google.common.primitives.Ints;

import java.util.List;

public class SortAlgorithm {

    public static void main(String[] args) {
        int[] numbers = {16,5,14,2,33,24,43,3,28,17,66};
        bubbleSort(numbers);
        selectionSort(numbers);
        insertionSort(numbers);
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

}


