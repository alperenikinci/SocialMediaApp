package com.alperenikinci.utility;

import com.alperenikinci.manager.IUserManager;
import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllData {

private final UserProfileService userProfileService;
private final IUserManager userManager;

   // @PostConstruct
    public void initData(){
        List<UserProfile> userProfiles=userManager.findAll().getBody();
        userProfileService.saveAll(userProfiles);
    }

}
