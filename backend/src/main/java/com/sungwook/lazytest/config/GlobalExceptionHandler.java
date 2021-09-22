package com.sungwook.lazytest.config;

import com.sungwook.lazytest.common.exceptions.ErrorResponse;
import com.sungwook.lazytest.common.exceptions.FailValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


/***
 * 공통 예외처리 핸들러
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FailValidation.class)
    public ResponseEntity<ErrorResponse> FailValidationHandler(FailValidation e){
        String message = "로직 처리 중에 유효성 검사를 실패했습니다";
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());

        log.error(String.format("%s -> %s", message, e.getMessage()));
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, message, errors);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}
