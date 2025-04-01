package com.example.demo.model;

public class ErrorResponse {
    private String message;
    private String detail;
    private int status;

    public ErrorResponse(String message, String detail, int status) {
        this.message = message;
        this.detail = detail;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
