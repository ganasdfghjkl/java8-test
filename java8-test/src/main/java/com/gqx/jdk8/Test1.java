package com.gqx.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Lambda表达式为Java 添加了缺失的函数式编程特性
 * 在其他编程语音中，Lambda 表达式的类型是函数,但在Java 中，
 * Lambda 表达式是对象，他们必须依附于一类特别的对象类型
 * ————函数是接口(functional interface)
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println("--------------java4-----------------");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 7, 8);
        /*
         * 外部迭代：
         *   对集合进行迭代，通过外部的迭代器进行迭代
         *   [ 1 | 2 | 3 | 4 | 5 | 6 ]
         *     ↑ → ↑ → ↑ → ↑ → ↑ → ↑ → ↑
         * */
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
        }
        System.out.println("---------------java7----------------");
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println("---------------java8----------------");
        /*
         * 关于函数式接口：
         *   1：如果一个接口只有一个抽象方法，那么该接口就是一个函数式接口；
         *   2：如果我们在某个接口上声明了FunctionalInterface注解，那么编译器就会按照函数式接口的定义来要求该接口；
         *   3：如果某个接口只有一个抽象方法，但我们并没有给该接口声明FunctionalInterface注解，编译器依旧会将他看作函数式接口；
         *   4: 函数式接口的实例可以通过Lambda表达式、方法引用或者构造方法应用来创建；
         * */
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("------->" + integer);
            }
        }
                // Lambda
                .andThen(i -> System.out.println(i + "<----------------"))
                // method reference 方法引用
                .andThen(System.out::println));

    }

}
