package com.example.demo.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * モデルクラス
 * Entity は DI 対象にしない（当たり前体操）
 */
@Getter
@Setter
public class Human implements Serializable {
    private String name;
    private Integer age;

    public Human(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}