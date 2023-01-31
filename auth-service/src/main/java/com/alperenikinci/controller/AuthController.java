package com.alperenikinci.controller;

import com.alperenikinci.dto.request.ActivateRequestDto;

import com.alperenikinci.dto.request.LoginRequestDto;
import com.alperenikinci.dto.request.RegisterRequestDto;
import com.alperenikinci.dto.request.UpdateByEmailOrUsernameRequestDto;
import com.alperenikinci.dto.response.ActivateResponseDto;

import com.alperenikinci.dto.response.LoginResponseDto;
import com.alperenikinci.dto.response.RegisterResponseDto;
import com.alperenikinci.dto.response.RoleResponseDto;
import com.alperenikinci.exception.AuthManagerException;
import com.alperenikinci.exception.ErrorType;
import com.alperenikinci.repository.enums.Status;
import com.alperenikinci.service.AuthService;
import com.alperenikinci.utility.JwtTokenManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.alperenikinci.constants.RestApi.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {


    private final AuthService authService;
    private final CacheManager cacheManager;
    private static int counter;
    private final JwtTokenManager jwtTokenManager;



    @GetMapping("/gettoken")
    public ResponseEntity<String> getToken(Long id){
        return  ResponseEntity.ok(jwtTokenManager.createToken(id));
    }

    @GetMapping("/getid")
    public ResponseEntity<Long> getId(String token){
        Optional<Long> id=jwtTokenManager.getUserId(token);
        if (id.isPresent()){
            return    ResponseEntity.ok(id.get());
        }else{
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }

    }

    @PostMapping(REGISTER)
    @Operation(summary = "Kullanici kayit eden metot")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return  ResponseEntity.ok( authService.register(dto));
    }

    @PostMapping(REGISTER+"withrabbitmq")
    @Operation(summary = "Kullanici kayit eden metot")
    public ResponseEntity<RegisterResponseDto> registerWithRabbitMq(@RequestBody @Valid RegisterRequestDto dto){
        return  ResponseEntity.ok( authService.registerWithRabbitMQ(dto));
    }


    @PostMapping(ACTIVATESTATUS)
    public  ResponseEntity<ActivateResponseDto> activateStatus(@RequestBody ActivateRequestDto dto){
        return  ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(LOGIN)
        public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
            return  ResponseEntity.ok(authService.login(dto));
        }

    @GetMapping(GETALLACTIVATESTATUS)
    public ResponseEntity<List<ActivateResponseDto>> getActiveStatus(){
        return ResponseEntity.ok(authService.getAllActivateStatus());
    }

    @GetMapping(GETALLBYSTATUS)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<ActivateResponseDto>> findAllByStatus(Status status){
        return ResponseEntity.ok(authService.findAllByStatus(status));
    }

/* PathVariable versiyonu;
    @GetMapping(GETALLBYSTATUS+"/{status}")
    public ResponseEntity<List<ActivateResponseDto>> findAllByStatus2(@PathVariable Status status){
        return ResponseEntity.ok(authService.findAllByStatus(status));
    }
 */

    @GetMapping(GETALLBYSTRINGSTATUS+"/{status}")
    public ResponseEntity<List<ActivateResponseDto>> findAllByStatus3(@PathVariable String status){
        return  ResponseEntity.ok(authService.findAllByStatusString(status));
    }

    @DeleteMapping(DELETEBYID+"/{authId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long authId) {
        return ResponseEntity.ok(authService.deleteAuthById(authId));
    }

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByEmailOrUsername(@RequestBody UpdateByEmailOrUsernameRequestDto dto){

        return ResponseEntity.ok(authService.updateByEmailOrUsername(dto));
    }

    @GetMapping(GETBYROLE)
    public ResponseEntity<List<RoleResponseDto>> getByRole(@PathVariable String role){

        return ResponseEntity.ok(authService.getByRole(role));
    }

    @GetMapping("/redis")
    @Cacheable(value ="redisexample")
    public String redisExample(String value){
        try {
            Thread.sleep(2000);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (counter==3){
            cacheManager.getCache("redisexample").clear();
            counter=1;
            return "Tüm veriler silindi";
        }
        return value  ;
    }

    @GetMapping("/redisdelete")
    @Cacheable(value = "redisdeleteexample")
    public Boolean redisDeleteExample(String value) {
        try{
            cacheManager.getCache("redisexample").evict(value); // sadece ilgili value siler.
//            cacheManager.getCache("redisexample").clear(); // her şeyi siler.
            return true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/redisdelete2")
//  @CacheEvict(cacheNames = "redisexample",allEntries = true)
    public Boolean redisDeleteExample(){
        try {
            //  cacheManager.getCache("redisexample").evict(value);//aynı isimli cahcde   tek bir degeri  silmek istediğimizde
            cacheManager.getCache("redisexample").clear();// aynı isimli cahcde butun degerleri silmek istediğimizde
            return true  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/redisdelete3")
    @CacheEvict(cacheNames = "redisexample",allEntries = true)
    public void  redisDeleteExample2(){

    }





}