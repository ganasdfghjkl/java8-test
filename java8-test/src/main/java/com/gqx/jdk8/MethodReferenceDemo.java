package com.gqx.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * 方法引用：    method reference
 * 方法引用可以看做是lambda表达式的语法糖
 *
 * 我们可以将方法引用看作是一个【函数指针】，function pointer
 *
 * @author gqx
 * @date 2020/8/7 15:39
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");
        list.forEach(System.out::println);

    }

}
