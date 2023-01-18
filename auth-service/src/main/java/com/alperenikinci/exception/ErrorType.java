package com.alperenikinci.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {


    INTERNAL_ERROR(5100,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parametre Hatası",HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4110,"Kullanıcı adı zaten var",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4111,"Boyle Bir kullanıcı Bulunamadı",HttpStatus.BAD_REQUEST),

    LOGIN_ERROR(4112,"Kullanıcı adı veya şifre Hatalı",HttpStatus.BAD_REQUEST),
    STATUS_NOT_FOUND(4113,"Boyle bir kullanici durumu bulunmamaktadır.",HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(4114,"Boyle bir kullanici rolu bulunmamaktadir.",HttpStatus.BAD_REQUEST),
    LOGIN_STATUS_ERROR(4115,"Yetkisiz kullanici girisi, lutfen hesabinizi aktif ediniz.",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4116,"Kullan?c? Olusturlamad?",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4117,"Gecersiz Token?",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
