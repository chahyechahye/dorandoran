package com.doran.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    /* 기본 */
    OK(HttpStatus.OK, "OK"),
    SUCCESS_FCM(HttpStatus.OK, "OK"),


    SUCCESS_CODE(HttpStatus.OK, "Success")
    ;


    private final HttpStatus httpStatus;
    private final String message;

}
