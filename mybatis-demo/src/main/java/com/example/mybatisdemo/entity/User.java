package com.example.mybatisdemo.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String gender;
    private Integer version;
    private Integer deleted;
}
