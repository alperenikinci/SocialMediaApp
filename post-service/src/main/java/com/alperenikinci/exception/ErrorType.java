package com.alperenikinci.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {


    INTERNAL_ERROR(5300,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4300,"Parametre Hatası",HttpStatus.BAD_REQUEST),
    POST_NOT_CREATED(4310,"Post oluşturulamadı",HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(4310,"Böyle bir post bulunamadı",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
