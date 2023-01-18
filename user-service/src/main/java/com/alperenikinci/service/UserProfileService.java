package com.alperenikinci.service;

import com.alperenikinci.dto.request.NewCreateUserDto;
import com.alperenikinci.dto.request.UpdateByEmailOrUsernameRequestDto;
import com.alperenikinci.dto.request.UpdateRequestDto;
import com.alperenikinci.dto.response.RoleResponseDto;
import com.alperenikinci.dto.response.UpdateResponseDto;
import com.alperenikinci.exception.ErrorType;
import com.alperenikinci.exception.UserManagerException;
import com.alperenikinci.manager.IAuthManager;
import com.alperenikinci.mapper.IUserMapper;
import com.alperenikinci.repository.IUserProfileRepository;
import com.alperenikinci.repository.entity.UserProfile;
import com.alperenikinci.repository.enums.Status;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {


    private  final IUserProfileRepository userProfileRepository;
    private final IAuthManager authManager;



    public UserProfileService(IUserProfileRepository userProfileRepository, IAuthManager authManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;

        this.authManager = authManager;
    }

    public Boolean createUser(NewCreateUserDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
        userProfile.setCreatedDate(System.currentTimeMillis());
        try {
            save(userProfile);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw  new UserManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    public Boolean activateStatus(Long authId) {
        Optional<UserProfile> userProfile=userProfileRepository.findOptionalByAuthId(authId);

        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return  true;
    }

    public UpdateResponseDto updateProfile(UpdateRequestDto dto){
        Optional<UserProfile> userProfile=userProfileRepository.findById(dto.getId());
            if(userProfile.isEmpty()){
                throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
            }
            userProfile.get().setName(dto.getName());
            userProfile.get().setAbout(dto.getAbout());
            userProfile.get().setAvatar(dto.getAvatar());
            userProfile.get().setEmail(dto.getEmail());
            userProfile.get().setUsername(dto.getUsername());
            userProfile.get().setPhone(dto.getPhone());
            userProfile.get().setAddress(dto.getAddress());
            userProfile.get().setUpdatedDate(System.currentTimeMillis());

           return IUserMapper.INSTANCE.toUpdateResponseDto(userProfile.get());

    }

    public Boolean deleteByAuthId(Long authId) {
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthId(authId);

        if(userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.DELETED);
        save(userProfile.get());
        return true;
    }

    public UpdateResponseDto updateProfile2(UpdateRequestDto dto){
        Optional<UserProfile> userProfile=userProfileRepository.findById(dto.getId());
        if(userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(!dto.getUsername().equals(userProfile.get().getUsername()) || !dto.getEmail().equals(userProfile.get().getEmail())){
            userProfile.get().setUsername(dto.getUsername());
            userProfile.get().setEmail(dto.getEmail());
            authManager.updateByEmailOrUsername(UpdateByEmailOrUsernameRequestDto.builder()
                            .email(dto.getEmail())
                            .username(dto.getUsername())
                    .build());
        }
        userProfile.get().setName(dto.getName());
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAvatar(dto.getAvatar());
        userProfile.get().setEmail(dto.getEmail());
        userProfile.get().setUsername(dto.getUsername());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAddress(dto.getAddress());
        userProfile.get().setUpdatedDate(System.currentTimeMillis());

        return IUserMapper.INSTANCE.toUpdateResponseDto(userProfile.get());

    }

    @Cacheable(value = "getbyrole", key = "#role.toUpperCase()")
    public List<UserProfile> getByRole(String role) {
        List<RoleResponseDto> roleResponseDtoList = authManager.getByRole(role).getBody();
        roleResponseDtoList.stream().forEach(x-> System.out.println(x));
        return roleResponseDtoList.stream().map(x -> userProfileRepository.findOptionalByAuthId(x.getId()).get()).collect(Collectors.toList());
    }

    @Cacheable(value = "getbystatuscache")
    public List<UserProfile> getByStatusCache() {
        try{
            Thread.sleep(3000);
        } catch (Exception e){

        }
        return userProfileRepository.findAllByStatus(Status.ACTIVE);
    }

    @Cacheable(value = "findallactiveprofile")
    public List<UserProfile> findAllActiveProfile() {

        return userProfileRepository.findAllActiveProfile();
    }
}