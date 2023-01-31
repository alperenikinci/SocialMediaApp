package com.alperenikinci.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateRequestDto {

    private String userId;
    private String title;
    private String content;
    private String postMediaUrl;
}
