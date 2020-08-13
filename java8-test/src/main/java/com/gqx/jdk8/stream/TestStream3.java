package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author gqx
 * @date 2020/8/13 15:48
 */
public class TestStream3 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        System.out.println(list.stream().mapToInt(a->a*2).reduce(0,Integer::sum));
        System.out.println(21);
        System.out.println(21<<1);

    }
}
