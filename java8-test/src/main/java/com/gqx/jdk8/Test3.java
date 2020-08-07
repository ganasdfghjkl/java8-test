package com.gqx.jdk8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gqx
 * @date 2020/8/6 16:46
 * lambda表达式的类型需要上下文来决定
 */
public class Test3 {
    public static void main(String[] args) {
        Test3Interface1 t1 = () -> {
        };
        System.out.println(t1.getClass().getInterfaces()[0]);
        Test3Interface2 t2 = () -> {
        };
        System.out.println(t2.getClass().getInterfaces()[0]);
        new Thread(() -> {
            System.out.println("hello word");
            System.out.println(Thread.currentThread().getName());
        }, "test3Thread").start();
        List<String> list1 = Arrays.asList("hello", "world", "hello world");
        list1.forEach(str -> System.out.println(str.toUpperCase()));

        List<User> list2 = new ArrayList<>();
        list2.add(User.builder().id(1).name("qwe").build());
        list2.add(User.builder().id(2).name("zxc").build());
        list2.forEach(a -> {
            a.setId(a.getId() * 2);
            a.setName(a.getName().toUpperCase());
        });
        list2.forEach(System.out::println);
        list1.stream().map(String::toUpperCase).map(User::new).forEach(System.out::println);
        //
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {
    int id;
    String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public User(String name) {
        this.name = name;
    }
}

@FunctionalInterface
interface Test3Interface1 {
    void myMethod();
}

@FunctionalInterface
interface Test3Interface2 {
    void myMethod2();
}
