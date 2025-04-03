package com.example.demo.controller.validator;

import org.springframework.stereotype.Component;

@Component
public class SomeControllerValidator {
    public void validate() throws Exception {
        throw new Exception("validation error!!!!!!!!");
    }
}
