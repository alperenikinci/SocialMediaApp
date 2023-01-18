package com.alperenikinci.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestDto implements Serializable {

    private    String id;
    @NotBlank
    @Size(min = 3 ,max=20 ,message = "Kullanici adi en az  3 karakter en fazla 20 karakter olabilir")
    private    String username;
    private    String name;
    @Email
    private    String email;
    private    String phone;
    private    String avatar;
    private    String address;
    private    String about;

}
