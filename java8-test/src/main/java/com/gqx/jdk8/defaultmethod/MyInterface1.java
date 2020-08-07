package com.gqx.jdk8.defaultmethod;

/**
 * @author gqx
 * @date 2020/8/7 16:41
 */
public interface MyInterface1 {

    default void myMethod(){
        System.out.println("default-myMethod-----------");
    }
}
