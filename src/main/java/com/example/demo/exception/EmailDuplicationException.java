package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailDuplicationException extends RuntimeException {

    public EmailDuplicationException(String message) {
        super(message);
    }
}
