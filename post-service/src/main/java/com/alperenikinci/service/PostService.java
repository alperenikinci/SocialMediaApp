package com.alperenikinci.service;

import com.alperenikinci.dto.request.PostCreateRequestDto;
import com.alperenikinci.dto.response.PostCreateResponseDto;
import com.alperenikinci.mapper.IPostMapper;
import com.alperenikinci.repository.IPostRepository;
import com.alperenikinci.repository.entity.Post;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PostService extends ServiceManager<Post,String> {
    private final IPostRepository repository;
    public PostService(IPostRepository repository){
        super(repository);
        this.repository = repository;
    }

    public PostCreateResponseDto createPost(PostCreateRequestDto dto) {

        Post post = IPostMapper.INSTANCE.toPost(dto);
        save(post);
        return IPostMapper.INSTANCE.toPostCreateResponseDto(post);
    }
}
