package com.gqx.jdk8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gqx
 * @date 2020/8/7 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    private String name;
    private List<Employee> employees;
}
