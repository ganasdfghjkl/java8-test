package com.gqx.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gqx
 * @date 2020/8/13 16:24
 */
public class TestStream4 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("1","2","3","4");
//        String[] strArr = stream.toArray(String[]::new);
//        Arrays.asList(strArr).stream().forEach(System.out::println);
//        System.out.println("------------------------");
//        List<String> list = stream.collect(Collectors.toList());
//        List<String> list = stream.parallel().collect(
//                () -> new ArrayList<>(),
//                (theList,item) -> {
//                    theList.add(item);
////                    System.out.println(theList);
////                    System.out.println(item);
//                }
//                ,(theList1,theList2)-> {
////
////                   theList1 = theList2;
//                    System.out.println(Thread.currentThread().getName()+"-theList1"+theList1);
//                    System.out.println(Thread.currentThread().getName()+"-theList2"+theList2);
//                    theList1.addAll(theList2);
//                    System.out.println(Thread.currentThread().getName()+"-theList1"+theList1);
//                    System.out.println(Thread.currentThread().getName()+"-theList2"+theList2);
//                }
//        );
//        list.forEach(System.out::println);

//        List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
//        list.forEach(System.out::println);
//        Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set.getClass());
//        set.forEach(System.out::println);

        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }


}
