/*
 * FileName: StringTest.java
 * Author:   zzw
 * Date:     2018年09月28日
 * Description:
 */
package com.zzw.web;

import java.util.regex.Pattern;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "csf;;@clnt;172.16.12.101;;com.lebo.chezhibao.org.user.service.UserService;getCacheUser~i;00;";
        String s1 = "redis;;172.16.10.161;rightPop;msg_85_dingtalk_1500_buffer;null;00";
        String[] split = Pattern.compile(";").split(s,-1);
        System.out.println(split[7]);
        String[] split2 = Pattern.compile(";").split(s1,-1);
        System.out.println(split2[6]);
    }
}
