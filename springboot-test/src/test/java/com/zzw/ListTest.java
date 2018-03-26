package com.zzw;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/3/13
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        change(list);
        //System.out.println(list.size());
        print(list);
    }

    public static void print(List<String> list){
        for (String s:list){
            System.out.println(s);
        }
    }

    public static void change(List<String> list){
        list = new ArrayList<>();
        list.add("2");
    }
}
