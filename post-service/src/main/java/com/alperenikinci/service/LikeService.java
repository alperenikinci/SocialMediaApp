package com.alperenikinci.service;

import com.alperenikinci.repository.ILikeRepository;
import com.alperenikinci.repository.entity.Like;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class LikeService extends ServiceManager<Like,String> {
    private final ILikeRepository repository;
    public LikeService(ILikeRepository repository){
        super(repository);
        this.repository = repository;
    }
}
