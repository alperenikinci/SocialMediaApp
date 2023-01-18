package com.alperenikinci.repository;

import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.repository.enums.Status;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,String> {


    Optional<UserProfile> findOptionalByAuthId(Long authId);


/*    @Query("{$or: [{status:?0},{name:?1}]}")
    List<UserProfile> findAllActiveProfile(String value,String name);*/

    @Query("{status:'ACTIVE'}")
    List<UserProfile> findAllActiveProfile();

    Optional<UserProfile> findOptionalByUsernameEqualsIgnoreCase(String username);

    List<UserProfile> findAllByEmailContainingIgnoreCase(String value);

    List<UserProfile> findAllByStatus(Status status);

    List<UserProfile> findAllByStatusOrAddress(Status status,String address);

}
