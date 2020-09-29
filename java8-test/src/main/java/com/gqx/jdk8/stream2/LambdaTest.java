package com.gqx.jdk8.stream2;

/**
 * @author gqx
 * @date 2020/9/29 14:01
 */
public class LambdaTest {

    Runnable r1 = ()-> System.out.println("r1"+this);

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("r2"+                          this);
        }
    };

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        Thread t1 = new Thread(lambdaTest.r1);
        t1.start();
        System.out.println("---------------------");
        Thread t2 = new Thread(lambdaTest.r2);
        t2.start();


    }

}
