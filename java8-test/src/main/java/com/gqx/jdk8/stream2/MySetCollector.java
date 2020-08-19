package com.gqx.jdk8.stream2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author gqx
 * @date 2020/8/19 15:48
 */
public class MySetCollector<T>
        implements
        Collector<T, Set<T>, Set<T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("================>调用Supplier");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("================>调用BiConsumer");
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("================>调用combiner");
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
        System.out.println("================>调用finisher");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("================>调用characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.UNORDERED));
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");
        Set<String> stringSet = list.stream().collect(new MySetCollector<>());

        System.out.println(stringSet);

    }

}
