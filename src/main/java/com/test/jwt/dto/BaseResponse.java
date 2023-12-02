package com.test.jwt.dto;

public class BaseResponse<T> {

    public BaseResponse(T t) {
        this.result = t;
    }

    private T result;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
