package com.alperenikinci.repository;


import com.alperenikinci.repository.entity.Dislike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDislikeRepository extends MongoRepository<Dislike,String> {
}
