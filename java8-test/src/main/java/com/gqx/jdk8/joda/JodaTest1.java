package com.gqx.jdk8.joda;

import jdk.nashorn.internal.scripts.JO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTest1 {
    // 标准UTC时间：2020-10-10T07:07:07.451Z

    public static Date convertUTC2Date(String utcDate){
        try {
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        }catch (Exception e){
            return null;
        }
    }

    public static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDate2LocalByDateFormat(Date javaDate,String dateFormat){
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }
    public static void main(String[] args) {
        System.out.println(convertUTC2Date("2020-10-10T07:07:07.451Z"));
        System.out.println(convertDate2UTC(new Date()));
        System.out.println(convertDate2LocalByDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }
}
