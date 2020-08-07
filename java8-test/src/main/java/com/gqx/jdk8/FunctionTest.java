package com.gqx.jdk8;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Objects;
import java.util.function.Function;

/**
 * 高阶函数：如果一个函数接收一个函数作为参数，或者返回一个函数作为返回值。那么该函数就是高阶函数
 *
 * @author gqx
 * @date 2020/8/7 9:54
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println(compute(2, value -> {
            return value * 2;
        }));
        System.out.println(compute(2, value -> value + 2));
        System.out.println(convert(3, value -> String.valueOf(value + "hello word")));


        Function<Integer, String> function = value -> value + "hello word";
        System.out.println(convert(123456, function
                .andThen(a -> a + "after")
                .compose(o -> o.hashCode())
        ));

    }

    public static int compute(int a, Function<Integer, Integer> function) {
        Objects.requireNonNull(function);
        // 调用function 的方法
        return function.apply(a);
    }

    public static String convert(int a, Function<Integer, String> function) {
        Objects.requireNonNull(function);
        // 调用function 的方法
        return function.apply(a);
    }
}
