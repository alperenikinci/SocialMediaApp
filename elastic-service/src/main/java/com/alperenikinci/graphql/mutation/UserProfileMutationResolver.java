package com.alperenikinci.graphql.mutation;

import com.alperenikinci.exception.ElasticManagerException;
import com.alperenikinci.exception.ErrorType;
import com.alperenikinci.graphql.model.UserProfileInput;
import com.alperenikinci.mapper.IUserMapper;
import com.alperenikinci.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

private final UserProfileService userProfileService;


    public  Boolean createUserProfile(UserProfileInput profile){
        try {
            userProfileService.save(IUserMapper.INSTANCE.toUserProfile(profile));
            return true;
        }catch (Exception e){
            throw  new ElasticManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

}
