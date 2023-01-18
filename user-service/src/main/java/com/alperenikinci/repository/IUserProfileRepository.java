package com.alperenikinci.repository;

import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.repository.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {

    Optional<UserProfile> findOptionalByAuthId(Long authId);
    List<UserProfile> findAllByStatus(Status status);

    /*    @Query("{$or: [{status:?0},{name:?1}]}")
        List<UserProfile> findAllActiveProfile(String value,String name);*/
    @Query("{status:'ACTIVE'}")
    List<UserProfile> findAllActiveProfile();




}
