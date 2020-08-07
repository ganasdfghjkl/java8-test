package com.gqx.jdk8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");

        /*
        * Lambda 表达式的基本结构
        * (param1,param2,param3...) -> { 代码块 }
        *
        * */
        jButton.addActionListener(e -> System.out.println("Button Pressed!"));
        frame.add(jButton);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
