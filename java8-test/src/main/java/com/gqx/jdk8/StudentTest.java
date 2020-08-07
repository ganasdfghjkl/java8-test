package com.gqx.jdk8;

import java.util.function.Supplier;

/**
 * @author gqx
 * @date 2020/8/7 14:29
 */
public class StudentTest {

    public static void main(String[] args) {
        Supplier<Student> supplier = () -> Student.builder()
                .name("qwe").age(123)
                .build();
        System.out.println(supplier.get());
        Supplier<Student> supplier2 = () -> new Student();
        Supplier<Student> supplier3 = Student::new;

        System.out.println(supplier.get());

    }

}
