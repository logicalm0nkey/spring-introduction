package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Something;

@Service
public class SomeService {
    public Something creaSomething(int id) {
        return new Something(id, 1, 1);
    }

    public double getSomethingWeight(Something something) {
        return something.getWeight();
    }
}
