package com.alperenikinci.exception;

import lombok.Getter;

@Getter
public class PostManagerException extends  RuntimeException {

    private final ErrorType errorType;


    public PostManagerException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public PostManagerException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
