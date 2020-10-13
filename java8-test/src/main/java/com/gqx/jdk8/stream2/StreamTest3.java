package com.gqx.jdk8.stream2;

import java.util.Arrays;
import java.util.List;

/**
 * @author gqx
 * @date 2020/8/25 8:56
 */
public  class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world"
        ,"teacher","welcome","person","student"
        );
    ///        list.stream().forEach(System.out::println);
        list.stream().distinct().filter(a->a.length()>5).map(a->a+"_abc").forEach(System.out::println);
    }
}
