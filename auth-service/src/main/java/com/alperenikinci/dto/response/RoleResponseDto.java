package com.alperenikinci.dto.response;

import com.alperenikinci.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleResponseDto {

    private Long id;
    private String username;
    private Roles role;
}
