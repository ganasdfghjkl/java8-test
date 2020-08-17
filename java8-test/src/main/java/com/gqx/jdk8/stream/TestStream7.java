package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author gqx
 * @date 2020/8/17 9:42
 */
public class TestStream7 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
//        list.stream().map(item->item.substring(0,1).toUpperCase()+item.substring(1)).forEach(System.out::println);
        Stream stream = list.stream().map(item ->{
            item = item.substring(0,1).toUpperCase()+item.substring(1);
            System.out.println("========>>>"+item+"<<<======");
            return item;
        }).map(item ->{
            item = item.substring(0,1).toLowerCase()+item.substring(1);
            System.out.println("========>>>"+item+"<<<======");
            return item;
        });

        System.out.println("============>>>执行终止操作<<<===========");
        stream.forEach(System.out::println);


    }

}
