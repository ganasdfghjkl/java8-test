package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author gqx
 * @date 2020/8/13 17:36
 */
public class TestStream5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "word", "hello word", "test");

        list.stream().parallel().forEach(a -> System.out.println(a.toUpperCase()));
        System.out.println("------------------");
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().parallel().forEach(a -> System.out.println(a * a));
        System.out.println("------------------");

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream
                .flatMap(theList -> theList.stream())
                .map(a -> a * a)
                .forEach(System.out::println);

    }

}
