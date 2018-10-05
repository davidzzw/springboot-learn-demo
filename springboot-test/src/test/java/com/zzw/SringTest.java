/*
 * FileName: SringTest.java
 * Author:   zzw
 * Date:     2018年07月03日
 * Description:
 */
package com.zzw;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class SringTest {

    public static void main(String[] args) {
        char[] chars = {'1', '4', '7'};
        System.out.println(chars.toString() == "147");
        String s = new String("123");
        s = "456";
        /*String s = new String(chars);
        String intern1 = s.intern();
        System.out.println(s == "147");

        String s2 = new String("258");
        String intern = s2.intern();
        System.out.println(s2 == "258");

        String a = new StringBuilder("1").append("2").append("3").append("4").toString();
        System.out.println(a == a.intern());

        String b = new StringBuilder("ja").append("va").toString();
        System.out.println(b == b.intern());*/
        //char[] chars2 = {'1', '2', '3'};
        //String s1 = "123";
        String s2 = new String ("123");
        System.out.println(s2 == s2.intern());
    }
}
