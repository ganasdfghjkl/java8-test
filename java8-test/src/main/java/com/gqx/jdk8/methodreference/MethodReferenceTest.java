package com.gqx.jdk8.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * 方法引用分为4类
 *   1.类名::静态方法名
 *   2.引用名(对象名)::实例方法名
 *   3.类名::实例方法名
 *   4.构造方法引用：类名::new
 *
 * @author gqx
 * @date 2020/8/7 15:51
 */
public class MethodReferenceTest {
    public static String getString(Supplier<String> supplier){
        return supplier.get()+"test";
    }

    public static String getString(String str , Function<String,String> function){
        return function.apply(str)+"test";
    }
    public static void main(String[] args) {
        Student s1 = Student.builder().name("zs").score(10).build();
        Student s2 = Student.builder().name("ls").score(21).build();
        Student s3 = Student.builder().name("ww").score(50).build();
        Student s4 = Student.builder().name("zl").score(33).build();
        List<Student> students = Arrays.asList(s1,s2,s3,s4);
        System.out.println("----------------使用lambda表达式进行排序---------------");
        students.sort((sp1,sp2)-> Student.compareScore(sp1,sp2));
        students.forEach(System.out::println);
        System.out.println("----------------使用静态方法引用进行排序---------------");
        students.sort(Student::compareName);
        students.forEach(System.out::println);

        System.out.println("----------------使用实例方法名引用进行排序---------------");
        StudentComparator studentComparator = new StudentComparator();
        students.sort((sp1,sp2) -> studentComparator.compareScore(sp1,sp2));
        students.sort(studentComparator::compareScore);
        students.forEach(System.out::println);

        System.out.println("----------------使用类名-实例方法引用进行排序---------------");
        // 使用第一个参数调用实例方法，后续参数做为参数方法
        students.sort(Student::compareByScore);

        students.sort((sp1,sp2)->sp2.compareByScore(sp1));

        students.forEach(System.out::println);

        List<String> cities = Arrays.asList("qingdao","chongqing","tianjin","beijing");
        cities.sort(String::compareTo);
        cities.forEach(System.out::println);
        System.out.println(getString(String::new));
        System.out.println(getString("hello world!!!",String::new));

    }
}
