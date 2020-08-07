package com.gqx.jdk8;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author gqx
 * @date 2020/8/7 11:40
 */
public class FunctionTest2 {
    public static void main(String[] args) {
        System.out.println("--------------Function---------------");
        System.out.println("q".hashCode());
        System.out.println(compute("q", integer -> integer * integer, s -> s.hashCode()));
        System.out.println(compute(3, integer -> integer * integer, s -> s + "-hello world!!!!"));
        System.out.println("--------------BiFunction---------------");
        System.out.println(compute(10, 5, (value, value2) -> value + value2));
        System.out.println(compute(10, 5, (value, value2) -> value - value2));
        System.out.println(compute(10, 5, (value, value2) -> value * value2));
        System.out.println(compute(10, 5, (value, value2) -> value / value2));
        System.out.println(compute(10, 5, (value, value2) -> value % value2));
        System.out.println(compute(11, 3, (value, value2) -> value % value2, value -> value + "-hello world !!!"));

    }

    public static int compute(String a,
                              Function<Integer, Integer> f1,
                              Function<String, Integer> f2) {
        return f1.compose(f2).apply(a);
    }

    public static String compute(int a,
                                  Function<Integer, Integer> f1, Function<Integer, String> f2) {
        return f1.andThen(f2).apply(a);
    }

    public static int compute(int a, int b,
                              BiFunction<Integer, Integer, Integer> function) {
        return function.apply(a, b);
    }

    public static String compute(int a, int b, BiFunction<Integer, Integer, Integer> bif, Function<Integer, String> f) {
        return bif.andThen(f).apply(a, b);
    }

}
