package com.alperenikinci.repository;

import com.alperenikinci.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICommentRepository extends MongoRepository<Comment,String> {
}
