package com.example.domain;

import lombok.Data;

@Data
public class RestResponse<T> {
    private String msg;
    private String code;
    private T data;

    public RestResponse(T data) {
        this.code = "200";
        this.msg = "success";
        this.data = data;
    }

    public RestResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public static  RestResponse success(Object data) {
        return new RestResponse<>(data);
    }

    public static RestResponse success() {
        return new RestResponse("success", "200");
    }

    public static  RestResponse fail(String message) {
        return new RestResponse(message, "500");
    }
}
