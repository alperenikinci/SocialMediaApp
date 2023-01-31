package com.alperenikinci.controller;

import com.alperenikinci.dto.request.PostCreateRequestDto;
import com.alperenikinci.dto.response.PostCreateResponseDto;
import com.alperenikinci.repository.entity.Post;
import com.alperenikinci.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alperenikinci.constant.ApiUrls.*;
@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping(CREATE)
    public ResponseEntity<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }
}
