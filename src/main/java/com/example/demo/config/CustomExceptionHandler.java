package com.example.demo.config;

import com.example.demo.domain.CommonDto;
import com.example.demo.exception.EmailDuplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Exception을 낚아채기
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonDto<String> illegalArgumentException(IllegalArgumentException e) {

        return new CommonDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = EmailDuplicationException.class)
    public CommonDto<String> emailDuplicationException(EmailDuplicationException e) {

        return new CommonDto<>(HttpStatus.FOUND.value(), e.getMessage());
    }
}
