package com.zzw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zzw
 * @see
 * @since 2018/1/30
 */
public class TimeTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.parse("2018-01-02").getTime());
        System.out.println(new Date().getTime());
    }
}
