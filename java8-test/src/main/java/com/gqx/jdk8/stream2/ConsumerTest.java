package com.gqx.jdk8.stream2;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @author gqx
 * @date 2020/9/1 16:17
 */
public class ConsumerTest {

    public static void test(Consumer<Integer> consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        Consumer<Integer> con = i -> System.out.println(i);

        IntConsumer conInt = i -> System.out.println(i);
        test(con);
        test((Consumer<Integer>) conInt::accept);

    }


}
