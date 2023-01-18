package com.alperenikinci.manager;

import com.alperenikinci.dto.request.UpdateByEmailOrUsernameRequestDto;
import com.alperenikinci.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.alperenikinci.constant.ApiUrls.GETBYROLE;
import static com.alperenikinci.constant.ApiUrls.UPDATEBYUSERNAMEOREMAIL;

@FeignClient(url = "${myapplication.feign.auth}", name= "auth-auth", decode404 = true)
public interface IAuthManager {

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByEmailOrUsername(@RequestBody UpdateByEmailOrUsernameRequestDto dto);
    @GetMapping(GETBYROLE)
    public ResponseEntity<List<RoleResponseDto>> getByRole(@PathVariable String role);

}
