package com.gqx.jdk8.stream2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author gqx
 * @date 2020/8/17 16:15
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Student s1 = Student.builder().name("zhangsan").score(80).build();
        Student s2 = Student.builder().name("lisi").score(90).build();
        Student s3 = Student.builder().name("wangwu").score(100).build();
        Student s4 = Student.builder().name("zhaoliu").score(90).build();
        List<Student> list = Arrays.asList(s1, s2, s3, s4);
        list.stream().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------------");
        System.out.println("count:"+list.stream().collect(counting()));
        System.out.println("count:"+list.stream().count());
        System.out.println("-----------------------------");


        List<TripDateDto> tripDateDtoList = Arrays.asList(
                TripDateDto.builder()
                        .year(2020)
                        .month(8)
                        .day(17)
                        .build(),
                TripDateDto.builder()
                        .year(2019)
                        .month(8)
                        .day(17)
                        .build(),
                TripDateDto.builder()
                        .year(2020)
                        .month(8)
                        .day(17)
                        .build(),
                TripDateDto.builder()
                        .year(2020)
                        .month(8)
                        .day(17)
                        .build(),
                TripDateDto.builder()
                        .year(2020)
                        .month(8)
                        .day(16)
                        .build()

        );
        List<TripDateDto> list21123 = tripDateDtoList.stream().distinct().collect(Collectors.toList());
        System.out.println(list21123);
    }

}
