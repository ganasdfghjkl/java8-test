package com.gqx.jdk8;

import java.util.*;

/**
 * lambda 表达式是一种匿名函数，他是没有声明的方法，
 * 既没有访问修饰符、返回修饰符和名字
 *
 * 作用：
 *  传递行为，而不仅仅是值
 *     提升抽象层次
 *     api重用性更好
 *     更加灵活
 * lambda表达式的基本语法:
 *     (argument) -> {body}
 *
 * lambda 结构
 *      一个lambda 表达式可以有零个或者多个参数
 *      参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a) 与(a) 效果相同
 *      所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a,b)或(String a,int b)
 *      空圆括号代表参数集为空。例如：() -> {body}
 *      当只有一个参数时，且类型可推导时，圆括号可以省略。例如：a->{body}
 *      lambda 表达式的主体可包含零条或多条语句
 *      如果lambda表达式的主体只有一条语句，花括号{}可以省略。匿名函数的返回类型与该主体表达式一致
 *      如果lambda表达式的主题包含一条以上的语句，则表达式必须包含在花括号{}中（形成代码块）。
 *  匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空。
 *
 *
 * @author
 */
public class StringComparator {
    public static void main(String[] args) {
        System.out.println("-----------------java8之前-----------------");
        List<String> stringList = Arrays.asList("1", "3", "2", "8", "7");
        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        stringList.stream().forEach(System.out::println);

        System.out.println("---------------java8---------------");

        // expression o1.compareTo(o1) 表达式
        // statement {return o1.compareTo(o2);} 代码块
        Collections.sort(stringList, (o1, o2) -> o2.compareTo(o1));

        // 方法引用的方式
        stringList.stream().forEach(System.out::println);

    }
}
