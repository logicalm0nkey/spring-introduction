package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * モデルクラス
 */
@Getter
@Setter
public class Human {
    private String name;
    private Integer age;

    public Human(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
