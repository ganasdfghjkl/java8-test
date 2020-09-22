package com.gqx.jdk8.stream2;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author gqx
 * @date 2020/8/25 8:56
 */
public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world"
        ,"teacher","welcome","person","student"
        );
//         list.stream().forEach(System.out::println);
        list.stream().map(a->a).forEach(System.out::println);

    }
}
