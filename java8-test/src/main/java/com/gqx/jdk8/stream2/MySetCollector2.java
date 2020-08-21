package com.gqx.jdk8.stream2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author gqx
 * @date 2020/8/19 17:21
 */
public class MySetCollector2<T>
        implements
        Collector<T, Set<T>, Map<T, T>> {

     volatile int i = 0;
    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println(Thread.currentThread().getName()+"================>调用Supplier");
        return ()->{
            System.out.println("------------------->"+(++i));
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println(Thread.currentThread().getName()+"================>调用BiConsumer");
        return (a,b)->{
            a.add(b);
            System.out.println(Thread.currentThread().getName()+"================>执行accumulator"+a);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println(Thread.currentThread().getName()+"================>调用combiner");
        return (a, b) -> {
            System.out.println(Thread.currentThread().getName()+"================>执行combiner "+a+b);
            a.addAll(b);
            return a;
        };
    }


    @Override
    public Set<Characteristics> characteristics() {
        System.out.println(Thread.currentThread().getName()+"================>调用characteristics");
        return Collections.unmodifiableSet(EnumSet.of( Characteristics.UNORDERED));
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println(Thread.currentThread().getName()+"================>调用finisher");

        return a -> {
            System.out.println(Thread.currentThread().getName()+"================>执行finisher");

            Map<T,T> map = new TreeMap<T,T>();
            a.stream().forEach(b -> map.put(b, b));
            return map;
        };
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<String> list = Arrays.asList("hello", "world", "hello world","a","s","w","g","w","q");
        Map map = list.parallelStream()
                .collect(new MySetCollector2<>());

        System.out.println(map);

    }
}
