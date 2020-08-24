package com.gqx.jdk8.stream2;

import javax.naming.SizeLimitExceededException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author gqx
 * @date 2020/8/24 17:38
 */
public class StreamTest2 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "hello world");

        NullPointerException nullPointerException = new NullPointerException("my exception");
        try (Stream<String> stream = list.stream();) {
            stream.onClose(
                    () -> {
                        System.out.println("aaaaaaaaaaaaa");
                        // throw  nullPointerException
                        throw new NullPointerException("first exception");
                    }
            ).onClose(() -> {
                System.out.println("bbbbbbbbbbb");
                // throw  nullPointerException
                throw new NullPointerException("second exception");
            }).onClose(() -> {
                System.out.println("bbbbbbbbbbb");
                int a = 12 / 0;
            })
                    .forEach(System.out::println);
        }


    }
}
