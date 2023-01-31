package com.alperenikinci.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateResponseDto {

    private String id;
    private String userId;
    private String username;
    private String title;
    private String content;
}
