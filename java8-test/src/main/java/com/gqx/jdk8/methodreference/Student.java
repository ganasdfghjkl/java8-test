package com.gqx.jdk8.methodreference;

import lombok.*;

/**
 * @author gqx
 * @date 2020/8/7 15:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    private String name;
    private int score;

    public static int compareScore(Student s1,Student s2){
        return s1.getScore() - s2.getScore();
    }
    public static int compareName(Student s1,Student s2){
        return s1.getName().compareTo(s2.getName());
    }

    public int compareByScore(Student s){
        return this.getScore() - s.getScore();
    }
    public int compareByName(Student s){
        return this.getName().compareTo(s.getName());

    }
}
