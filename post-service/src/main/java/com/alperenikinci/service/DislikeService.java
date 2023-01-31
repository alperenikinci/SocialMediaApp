package com.alperenikinci.service;

import com.alperenikinci.repository.IDislikeRepository;
import com.alperenikinci.repository.ILikeRepository;
import com.alperenikinci.repository.entity.Dislike;
import com.alperenikinci.repository.entity.Like;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class DislikeService extends ServiceManager<Dislike,String> {
    private final IDislikeRepository repository;
    public DislikeService(IDislikeRepository repository){
        super(repository);
        this.repository = repository;
    }
}
