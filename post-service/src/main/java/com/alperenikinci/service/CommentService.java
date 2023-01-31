package com.alperenikinci.service;

import com.alperenikinci.repository.ICommentRepository;
import com.alperenikinci.repository.entity.Comment;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceManager<Comment,String> {
    private final ICommentRepository repository;
    public CommentService(ICommentRepository repository){
        super(repository);
        this.repository = repository;
    }
}
