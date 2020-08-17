package com.gqx.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gqx
 * @date 2020/8/17 15:10
 * <p>
 * Stream  分组操作
 */
public class TestStream13 {

    public static void main(String[] args) {
        List<Student> list =
                Arrays.asList(
                        Student.builder()
                                .name("zhangsan")
                                .age(20)
                                .score(100)
                                .build(),
                        Student.builder()
                                .name("lisi")
                                .age(20)
                                .score(90)
                                .build(),
                        Student.builder()
                                .name("wangwu")
                                .age(30)
                                .score(90)
                                .build(),
                        Student.builder()
                                .name("zhangsan")
                                .age(40)
                                .score(81)
                                .build()
                );

//        Map<String,List<Student>> map =  list.stream().collect(Collectors.groupingBy(Student::getName));
//        System.out.println(map);


//        Map<String,Long> map = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
//        System.out.println(map);


//        Map<String,Double> map = list.stream()
//                .collect(
//                        Collectors.groupingBy(Student::getName,Collectors.averagingDouble(Student::getScore))
//                );
//        System.out.println(map);


//        Map<Boolean,List<Student> > map = list.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 90));
//        System.out.println(map);



    }
}
