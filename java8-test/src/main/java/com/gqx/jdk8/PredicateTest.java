package com.gqx.jdk8;

import java.util.function.Predicate;

/**
 *
 * @author gqx
 * @date 2020/8/7 14:15
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = a-> a.length() >5 ;

        System.out.println(predicate.test("hello world"));
    }
}
