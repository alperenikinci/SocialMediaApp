package com.alperenikinci.dto.response;

import com.alperenikinci.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateResponseDto {
    private    String username;
    private    String name;
    private    String email;
    private    String phone;
    private    String avatar;
    private    String address;
    private    String about;
    long updatedDate;
    private Status status;
}
