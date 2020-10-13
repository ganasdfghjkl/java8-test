package com.gqx.jdk8;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author gqx
 * @date 2020/8/7 15:26
 */
public class OptionalTest2 {
    public static void main(String[] args) {
        Employee e1 = Employee.builder().name("zs").build();
        Employee e2 = Employee.builder().name("ls").build();
        Company company = Company.builder()
                .name("comp1")
                .employees(Arrays.asList(e1, e2))
                .build();
        Optional<Company> optional = Optional.ofNullable(company);
        System.out.println(
                        optional.map(Company::getEmployees)
                .orElse(Collections.emptyList())
        );
    }
}
