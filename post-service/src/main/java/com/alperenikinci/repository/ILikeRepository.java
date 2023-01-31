package com.alperenikinci.repository;


import com.alperenikinci.repository.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILikeRepository extends MongoRepository<Like,String> {
}
