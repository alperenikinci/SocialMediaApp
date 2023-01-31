package com.alperenikinci.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Post {

    @Id
    private String id;
    private String userId;
    private String username;
    private String title;
    private String postMediaUrl;
    private String content;
    private int likeCount;
    private int dislikeCount;
    @Builder.Default
    private long sharedTime = System.currentTimeMillis();
}
