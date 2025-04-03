package com.example.demo.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.example.demo.model.Human;

@Service
public class WebApiService implements Serializable {

    public Human searchHumanByName(String name) {
        // 名前検索した体の戻り値
        return new Human(name, Integer.valueOf(20));
    }
}
