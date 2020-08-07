package com.gqx.jdk8.defaultmethod;

/**
 * 对于默认方法，类的优先级大于接口
 * @author gqx
 * @date 2020/8/7 16:42
 */
public class MyClass extends MyInterface1Impl implements MyInterface2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }

    @Override
    public void myMethod() {
        MyInterface2.super.myMethod();
    }
}
