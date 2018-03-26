package com.zzw;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzw
 * @see
 * @since 2018/3/13
 */
public class HashMapTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Map<String,String> map = new HashMap<>();
        map.put("1","2");
        map.put("2","3");
        System.out.println(map);
        try {
            Field filed = map.getClass().getDeclaredField("table");
            Class<?> clazz = null;
            try {
                clazz = Class.forName("java.util.HashMap$Node");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            HashMap m = new HashMap<String, String>();
            try {
                filed.setAccessible(true);
                Object[] O = ((Object[]) filed.get(map));
                Field fNode = null;
                for (Object o : O) {
                    if (o != null) {
                        System.out.println(o);
                        fNode = clazz.getDeclaredField("next");
                        fNode.setAccessible(true);
                        while ((o = fNode.get(o)) != null) {
                            System.out.println(o);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(clazz.getDeclaredClasses());
            System.out.println(filed);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
