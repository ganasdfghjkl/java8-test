package com.gqx.jdk8.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 */
public class TestStream6 {
    public static void main(String[] args) {
//        Stream<String> stream = Stream.generate(()-> UUID.randomUUID().toString());
//        stream.findFirst().ifPresent(System.out::println);

//        Stream<Integer> stream = Stream.iterate(1,
//                item -> item+2).limit(20);
//        stream.forEach(System.out::println);

        Integer integer = Stream.iterate(1, item -> item+2)
                .limit(20).filter(a->a>2).mapToInt(a->a*2)
                .skip(2).limit(2).sum();
        System.out.println(integer);
//        IntSummaryStatistics summaryStatistics = Stream.iterate(1, item -> item+2)
//                .limit(20).filter(a->a>2).mapToInt(a->a*2)
//                .skip(2).limit(2).summaryStatistics();
//
//        System.out.println(summaryStatistics.getAverage());
//        System.out.println(summaryStatistics.getMax());
//        System.out.println(summaryStatistics.getSum());





    }
}
