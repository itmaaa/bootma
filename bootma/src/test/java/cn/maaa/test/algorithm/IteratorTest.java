package cn.maaa.test.algorithm;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Description:
 *
 * @author mazh
 * @date 2021/2/3 10:37
 */
public class IteratorTest {

    public static void main(String[] args) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(3);
        Iterator<Integer> iterator = hashSet.iterator();
        for (int i = 0; i < 10 ; i++) {
            //报错
            //System.out.println( iterator.next());
            System.out.println( hashSet.iterator().next());
        }
    }
}
