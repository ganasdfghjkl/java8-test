package com.gqx.jdk8;

import java.util.Optional;

/**
 * @author gqx
 * @date 2020/8/7 15:14
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");
        optional.ifPresent(System.out::println);
        System.out.println("-----------------------");
        System.out.println(optional.orElse("hello"));
        System.out.println("-----------------------");
        System.out.println(optional.orElseGet(()->"world------"));
    }
}
