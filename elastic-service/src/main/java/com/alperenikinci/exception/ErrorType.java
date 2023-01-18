package com.alperenikinci.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {


    INTERNAL_ERROR(5200,"Sunucu Hatasi", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4300,"Parametre Hatas�",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4310,"Kullan�c� Olusturlamad�",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4310,"Boyle Bir Kullan�c� Bulunamad�",HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    HttpStatus httpStatus;
}
