package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gqx
 * @date 2020/8/17 13:56
 */
public class TestStream11 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "hello welcome",
                "world hello",
                "hello world hello",
                "hello world");
        list.stream().flatMap(a-> Arrays.stream(a.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);

    }
}
