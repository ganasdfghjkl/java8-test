package com.gqx.jdk8.stream;

import java.util.stream.IntStream;

/**
 * @author gqx
 * @date 2020/8/13 15:39
 */
public class TestStream2 {

    public static void main(String[] args) {
        IntStream.of(new int[]{5,6,7}).forEach(System.out::println);
        System.out.println("------------------");
        IntStream.range(3,6).forEach(System.out::println);
        System.out.println("------------------");
        IntStream.rangeClosed(3,6).forEach(System.out::println);

    }

}
