package com.gqx.jdk8.stream;

import lombok.*;

/**
 * @author gqx
 * @date 2020/8/17 15:16
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private int score;
    private int age;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
