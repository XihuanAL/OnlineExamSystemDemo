package com.example.domain;

import lombok.Data;

@Data
public class RestResponse<T> {
    private String message;
    private String code;
    private T data;

    public RestResponse(T data) {
        this.code = "200";
        this.message = "success";
        this.data = data;
    }

    public RestResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public static RestResponse success(Object data) {
        return new RestResponse(data);
    }

    public static RestResponse success() {
        return new RestResponse("success", "200");
    }
}
