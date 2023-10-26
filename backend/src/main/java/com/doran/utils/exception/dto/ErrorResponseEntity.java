package com.doran.utils.exception.dto;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponseEntity {
    private int statusCode;
    private String statusName;
    private String message;

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
            .status(e.getHttpStatus())
            .body(ErrorResponseEntity.builder()
                                     .statusCode(e.getHttpStatus().value())
                                     .statusName(e.name())
                                     .message(e.getMessage())
                                     .build());
    }
}
