package com.alperenikinci.controller;

import com.alperenikinci.dto.request.NewCreateUserDto;
import com.alperenikinci.dto.request.UpdateRequestDto;
import com.alperenikinci.dto.response.UpdateResponseDto;
import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alperenikinci.constant.ApiUrls.*;
@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {


private  final UserProfileService userProfileService;


    @GetMapping(FINDALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){

        return ResponseEntity.ok(userProfileService.findAll());
    }
    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> create(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.save(userProfile));
    }
}
