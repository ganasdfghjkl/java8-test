package com.gqx.jdk8.defaultmethod;

/**
 * @author gqx
 * @date 2020/8/7 16:43
 */
public interface MyInterface2 {
    default void myMethod(){
        System.out.println("default-myMethod2222-----------");
    }
}
