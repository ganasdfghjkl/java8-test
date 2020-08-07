package com.gqx.jdk8;

/**
 * @author gqx
 * @date 2020/8/6 15:29
 */
public class Test2 {
    public void myTest(MyInterface myInterface){
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.myTest(() -> System.out.println("MyTest"));
        System.out.println("----------------------------");
        MyInterface myInterface = () -> System.out.println("hello");
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
        MyInterface.test3("asdasd");
    }

}
/**
 * 如果一个接口声明了一个抽象方法，但是这个抽象方法复写了Object 的一个公共方法
 * 是不会让抽象方法+1的。，所以下面这个例子也是一个 函数式接口
 */
@FunctionalInterface
interface MyInterface {
    /**
     * 函数式接口的测试
     */
    void test();

    /**
     * 重写Object 的 toString方法,测试重写Object的方法 是否仍然是函数式接口
     * @return
     */
    @Override
    String toString();

    static void test3(String str){
        System.out.println(str);
    }

}
