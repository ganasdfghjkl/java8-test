package com.gqx.jdk8;

import lombok.*;

/**
 * @author gqx
 * @date 2020/8/7 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Person {
    private String username;
    private Integer arg;

}
