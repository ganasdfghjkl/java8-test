package com.gqx.jdk8.java8Time;


import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

public class Java8TimeTest {
    public static void main(String[] args) {
        LocalDate localDate1 = LocalDate.now();
        System.out.println(localDate1);
        System.out.println(localDate1.getYear()
                + "," + localDate1.getMonthValue() + "," + localDate1.getDayOfMonth());
        System.out.println("--------------");
        LocalDate localDate2 = LocalDate.of(2020, 01, 01);
        System.out.println(localDate2);
        System.out.println("--------------");
        LocalDate localDate3 = LocalDate.of(2020, 10, 25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(), localDate3.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(20000, 10, 15));
        if (monthDay.equals(monthDay2)) {
            System.out.println("equals  true");
        } else {
            System.out.println("equals  false");
        }
        System.out.println("--------------");
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalTime time2 = time.plusHours(3).plusMinutes(20);
        System.out.println(time2);
        System.out.println("--------------");

        LocalDate localDate4 = localDate1.plus(2, ChronoUnit.WEEKS);
        System.out.println(localDate4);
        System.out.println("--------------");
        LocalDate localDate5 = localDate1.minus(2, ChronoUnit.MONTHS);
        System.out.println(localDate5);
        System.out.println("--------------");
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println("--------------");
        LocalDate localDate6 = LocalDate.now();
        LocalDate localDate7 = LocalDate.of(2090, 10, 28);
        System.out.println(localDate6.isAfter(localDate7));
        System.out.println(localDate6.isBefore(localDate7));
        System.out.println(localDate6.equals(localDate7));
        System.out.println("--------------");
        Set<String> set = ZoneId.getAvailableZoneIds();
        Set<String> treeSet = new TreeSet<String>() {
            {
                addAll(set);
            }
        };
        treeSet.forEach(System.out::println);
        System.out.println("--------------");
        ZoneId zoneId = ZoneId.of("America/Recife");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
        System.out.println("--------------");

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());
        System.out.println("--------------");
        YearMonth yearMonth1 = YearMonth.of(2020, 2);
        System.out.println(yearMonth1);
        System.out.println(yearMonth1.lengthOfMonth());
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println(yearMonth1.isLeapYear());
        System.out.println("--------------");
        LocalDate localDate8 = LocalDate.now();
        LocalDate localDate9 = LocalDate.of(2021,12,3);
        Period period = Period.between(localDate8,localDate9);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println("--------------");
        System.out.println(Instant.now());

    }
}
