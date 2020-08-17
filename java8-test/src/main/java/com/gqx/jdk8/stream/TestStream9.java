package com.gqx.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author gqx
 * @date 2020/8/17 11:26
 */
public class TestStream9 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i <5000000 ; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long startTime = System.nanoTime();
        list.parallelStream().sorted().count();
        long endTime = System.nanoTime();

//        long start1Time = System.nanoTime();
//        list.stream().parallel().sorted().count();
//        long end1Time = System.nanoTime();


        System.out.println("不使用并行->"+(endTime-startTime));



    }
}
