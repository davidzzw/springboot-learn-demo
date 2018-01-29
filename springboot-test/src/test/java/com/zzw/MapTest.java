package com.zzw;

import java.util.*;

/**
 * @author zzw
 * @see
 * @since 2018/1/29
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        Collection<String> values = map.values();
    }
}
