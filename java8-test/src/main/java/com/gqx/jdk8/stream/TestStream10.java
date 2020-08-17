package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author gqx
 * @date 2020/8/17 11:44
 */
public class TestStream10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");

//        list.stream().filter(a->a.length() == 5).limit(1).forEach(System.out::println);
//        list.parallelStream().filter(a->a.length() == 5).limit(1).forEach(System.out::println);
        list.stream().mapToInt(item ->{
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length  -> length == 5).findFirst().ifPresent(System.out::println);

        list.parallelStream().mapToInt(item ->{
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


    }
}
