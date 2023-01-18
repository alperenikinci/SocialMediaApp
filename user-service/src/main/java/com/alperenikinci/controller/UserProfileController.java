package com.alperenikinci.controller;

import com.alperenikinci.dto.request.NewCreateUserDto;
import com.alperenikinci.dto.request.UpdateRequestDto;
import com.alperenikinci.dto.response.UpdateResponseDto;
import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.repository.enums.Status;
import com.alperenikinci.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import  static  com.alperenikinci.constant.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final CacheManager cacheManager;


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }

    @PostMapping(ACTIVATESTATUSBYID)
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId){
        return  ResponseEntity.ok(userProfileService.activateStatus(authId));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<UpdateResponseDto> update(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile(dto));
    }

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<UpdateResponseDto> update2(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile2(dto));
    }

    @PutMapping(UPDATE+"withrabbitmq")
    public  ResponseEntity<UpdateResponseDto> updateWithRabbitMQ(@RequestBody @Valid UpdateRequestDto dto){
        return  ResponseEntity.ok(userProfileService.updateProfileWithRabbitMQ(dto));
    }

    @DeleteMapping(DELETEBYAUTHID+"/{authId}")
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long authId){
       return ResponseEntity.ok(userProfileService.deleteByAuthId(authId));
    }

    @GetMapping(GETBYROLE)
    public ResponseEntity<List<UserProfile>> getByRole(@PathVariable String role){
        return ResponseEntity.ok(userProfileService.getByRole(role));
    }

    @GetMapping("/getbystatus")
    public ResponseEntity<List<UserProfile>> getByStatusCache(){
        return ResponseEntity.ok(userProfileService.getByStatusCache());
    }
    @GetMapping("/redisdelete")
//  @CacheEvict(cacheNames = "redisexample",allEntries = true)
    public Boolean redisDeleteExample(){
        try {
            //  cacheManager.getCache("redisexample").evict(value);//aynı isimli cahcde   tek bir degeri  silmek istediğimizde
            cacheManager.getCache("getbystatuscache").clear();// aynı isimli cahcde butun degerleri silmek istediğimizde
            return true  ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/findallactiveprofile")
    public ResponseEntity<List<UserProfile>> findAllActiveProfile(){

        return ResponseEntity.ok(userProfileService.findAllActiveProfile());
    }
}
