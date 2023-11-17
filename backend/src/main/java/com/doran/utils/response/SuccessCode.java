package com.doran.utils.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    /* 기본 */
    OK(HttpStatus.OK, "OK"),
    SUCCESS_FCM(HttpStatus.OK, "OK"),

    SUCCESS_CODE(HttpStatus.OK, "Success");

    private final HttpStatus httpStatus;
    private final String message;

}
