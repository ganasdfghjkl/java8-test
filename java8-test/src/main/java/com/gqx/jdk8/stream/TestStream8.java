package com.gqx.jdk8.stream;

import java.util.stream.IntStream;

/**
 * @author gqx
 * @date 2020/8/17 9:57
 */
public class TestStream8 {
    public static void main(String[] args) {
//        IntStream.iterate(0,i->(i+1) %2).distinct().limit(9).forEach(System.out::println);
        IntStream.iterate(0,i->(i+1) %2).limit(9).distinct().forEach(System.out::println);

    }

}
