package com.alperenikinci;

import com.alperenikinci.utility.JwtTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class,args);
            JwtTokenManager jwtTokenManager = new JwtTokenManager();
            System.out.println(jwtTokenManager.createToken(1L));

    }

}
