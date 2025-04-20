package com.univiser.test.inventory.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private int code;
    private String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
