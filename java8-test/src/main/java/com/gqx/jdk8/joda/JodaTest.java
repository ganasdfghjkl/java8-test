package com.gqx.jdk8.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.Date;

/**
 * @author gqx
 * @date 2020/10/21 14:37
 */
public class JodaTest {

    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));

        System.out.println("--------");

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));
        System.out.println("--------");
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        System.out.println("------------");

        localDate = localDate.plusMonths(2).dayOfMonth().withMaximumValue();
        System.out.println(localDate);
        System.out.println("----------");

        DateTime d2 = new DateTime();
        DateTime d3 = d2.minusYears(2).monthOfYear().setCopy(2).dayOfMonth().withMaximumValue();
        System.out.println(d3.toString("yyyy-MM-dd"));


    }
}
