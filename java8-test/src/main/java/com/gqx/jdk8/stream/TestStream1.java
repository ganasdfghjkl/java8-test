package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author gqx
 * @date 2020/8/13 15:13
 *
 * stream  流由三部分构成
 * 1：源
 * 2：零个或多个中间操作
 * 3：终止操作
 * 流操作分类
 * 1：惰性求值
 *  stream.xxx().yyyy().zzz()
 *  当没有终止操作时。，是不会去执行的
 * 2：及早求值
 *  stream.xxx().yyyy().zzz().count()
 *
 *
 *  1.Collection提供了新的stream()方法
 *  2.流不储存值，通过管道的方法获取值
 *  3.本质是函数式的，对流的操作会生成一个结果，不过并不会修改底层的数据源，集合可以作为流的底层数据源
 *  4.延迟查找，很多流操作(过滤、映射、排序等)都可以延迟实现
 *
 *
 */
public class TestStream1 {
    public static void main(String[] args) {
        /**
         * 得到 stream 的三种方式
         */
        Stream stream1 = Stream.of("hello","word","hello word");

        String[] myArr = new String[]{"hello","word","hello word"};
        Stream stream2 = Stream.of(myArr);
        Stream stream3 = Arrays.stream(myArr);

        List<String> list = Arrays.asList(myArr);
        Stream stream4 = list.stream();

    }
}
