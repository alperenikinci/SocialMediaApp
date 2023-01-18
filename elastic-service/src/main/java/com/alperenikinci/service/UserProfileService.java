package com.alperenikinci.service;


import com.alperenikinci.exception.ElasticManagerException;
import com.alperenikinci.exception.ErrorType;
import com.alperenikinci.repository.IUserProfileRepository;
import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.repository.enums.Status;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private  final IUserProfileRepository userProfileRepository;


    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;

    }


    public List<UserProfile> findAllContainingEmail(String value) {

        return  userProfileRepository.findAllByEmailContainingIgnoreCase(value);
    }

    public List<UserProfile> findAllByStatus(String status) {

        return  userProfileRepository.findAllByStatus(Status.valueOf(status.toUpperCase(Locale.ENGLISH)));

    }

    public List<UserProfile> findAllByStatusOrAddress(String status, String address) {

        return  userProfileRepository.
                findAllByStatusOrAddress(Status.valueOf(status.toUpperCase(Locale.ENGLISH)),address);
    }

    public  UserProfile findByUsername(String username){
        UserProfile userProfile;
           try {
               userProfile =userProfileRepository.findOptionalByUsernameEqualsIgnoreCase(username).get();
           }catch (Exception e){
                throw  new ElasticManagerException(ErrorType.USER_NOT_FOUND);
           }
           return  userProfile;
    }
}
