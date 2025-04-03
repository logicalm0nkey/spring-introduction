package com.example.demo.model;

import java.io.Serializable;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Something implements Serializable {
    @Positive
    private int id;
    @Positive
    private long value;
    @Positive
    private double weight;

    public Something(int id, double weight, long value) {
        this.id = id;
        this.value = value;
        this.weight = weight;
    }
}
