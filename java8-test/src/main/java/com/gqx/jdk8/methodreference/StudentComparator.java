package com.gqx.jdk8.methodreference;

/**
 * @author gqx
 * @date 2020/8/7 16:03
 */
public class StudentComparator {
    public int compareScore(Student s1,Student s2){
        return s1.getScore() - s2.getScore();
    }
    public int compareName(Student s1,Student s2){
        return s1.getName().compareTo(s2.getName());
    }
}
