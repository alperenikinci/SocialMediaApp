package com.alperenikinci.repository;

import com.alperenikinci.repository.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPostRepository extends MongoRepository<Post,String> {
}
