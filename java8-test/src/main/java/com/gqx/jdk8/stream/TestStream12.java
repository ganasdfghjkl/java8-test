package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author gqx
 * @date 2020/8/17 14:56
 */
public class TestStream12 {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi","Hello","你好");
        List<String> list2 = Arrays.asList("zhangsan","lisi","zhaoliu","wangwu");
        list1.parallelStream().flatMap(a->list2.stream().map(b->a+"---"+b))
                .forEach(System.out::println);


    }
}
