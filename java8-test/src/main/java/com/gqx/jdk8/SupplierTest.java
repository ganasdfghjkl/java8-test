package com.gqx.jdk8;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * @author gqx
 * @date 2020/8/7 14:26
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = () ->"hello world";
        System.out.println(supplier.get());
        BinaryOperator<Integer> binaryOperator = (a,b) -> a+b;
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        Comparator<String> comparator2 = Comparator.comparingInt(a -> a.charAt(0));



    }
}
