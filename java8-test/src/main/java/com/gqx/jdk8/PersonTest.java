package com.gqx.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author gqx
 * @date 2020/8/7 13:43
 */
public class PersonTest {

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(Person.builder()
                .username("zs").arg(20)
                .build(), Person.builder()
                .username("ls")
                .arg(40)
                .build(), Person.builder()
                .username("ww")
                .arg(60)
                .build());
        System.out.println("----------------根据name检索---------------------");
        getPersonsByUsername("zs", personList).forEach(System.out::println);
        System.out.println("---------------------根据年龄检索-----------------------");
        getPersonsByAge(30,personList).forEach(System.out::println);
        System.out.println("------------------------------------------------");
        getPersonsByAge(41,personList,(age,persons)->persons.stream().filter(person -> person.getArg()<=age).collect(Collectors.toList())).forEach(System.out::println);

    }

    public static List<Person> getPersonsByUsername(String username, List<Person> personList) {
        return personList.stream().filter(person -> person.getUsername().equals(username)).collect(Collectors.toList());
    }

    public static List<Person> getPersonsByAge(Integer age, List<Person> personList) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction =
                (ageOfPerson, persons) ->
                        persons.stream()
                                .filter(person -> person.getArg() >= age)
                                .collect(Collectors.toList());
        return biFunction.apply(age, personList);
    }

    public static List<Person> getPersonsByAge(Integer age, List<Person> personList,BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, personList);
    }
}
