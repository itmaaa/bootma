package cn.maaa.test.algorithm;

import java.util.*;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2020年05月09日 11:45
 */
public class WeightedRoundRobin {

    public static void main(String[] args) {
        System.out.println("抽中机器下标："+ getNextWeightedObject(new int[]{1,2,3}));
    }

    public static int getNextWeightedObject(int[] weights){
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            map.put(i,sum );
        }
        System.out.println("sum = " + sum);
        int random = new Random().nextInt(sum);
        if(random == 0) return 0;
        System.out.println("random =" + random);
        for (Integer key : map.keySet()) {
             if(map.get(key) > random){
                 System.out.println("概率 ：" + (double)(map.get(key) - map.get(key-1)) / sum);
                 return key;
             }
        }
        return 0;
    }
}
