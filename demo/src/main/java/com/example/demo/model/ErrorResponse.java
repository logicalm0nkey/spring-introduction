package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String detail;
    private int status;

    public ErrorResponse(String message, String detail, int status) {
        this.message = message;
        this.detail = detail;
        this.status = status;
    }
}
