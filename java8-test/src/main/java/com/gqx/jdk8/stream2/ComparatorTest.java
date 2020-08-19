package com.gqx.jdk8.stream2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gqx
 * @date 2020/8/19 14:06
 */
public class ComparatorTest {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("nihao","hello","world","welcome");

//        list.sort(Comparator.comparing(String::length).reversed().thenComparing(Comparator.comparingInt(String::hashCode).reve rsed()));
//        list.sort(Comparator.comparingInt(String::length ));
//        list.sort(Comparator.comparingInt(a->a.length() ));
//        Collections.sort(list,Comparator.comparingInt((String a)->a.length()).reversed());




        System.out.println(list);

    }

}
