package com.zzw;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/3/5
 */
public class ForTest {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = Arrays.asList(ints);
        List<Integer> list2 = Arrays.asList(ints);
        for (int i = 0;i < list.size();i ++){
            for (int j = 0;j < list2.size();j ++){
                if (list.get(i) == list2.get(j)){
                    System.out.println(list.get(i) + "==========" + list2.get(j));
                    break;
                }
            }
        }
    }
}
