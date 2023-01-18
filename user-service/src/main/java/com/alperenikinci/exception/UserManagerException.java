package com.alperenikinci.exception;

import lombok.Getter;

@Getter
public class UserManagerException extends  RuntimeException {

    private final ErrorType errorType;


    public UserManagerException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public UserManagerException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
