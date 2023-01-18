package com.alperenikinci.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {


    INTERNAL_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatası",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4210,"Kullanıcı oluşturulamadı",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4210,"Böyle bir kullanıcı bulunamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4212,"Gecersiz Token",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
