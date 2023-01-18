package com.alperenikinci.mapper;

import com.alperenikinci.dto.request.NewCreateUserDto;
import com.alperenikinci.dto.request.RegisterRequestDto;
import com.alperenikinci.dto.response.ActivateResponseDto;
import com.alperenikinci.dto.response.LoginResponseDto;
import com.alperenikinci.dto.response.RegisterResponseDto;
import com.alperenikinci.dto.response.RoleResponseDto;
import com.alperenikinci.rabbitmq.model.NewCreateUserModel;
import com.alperenikinci.rabbitmq.model.UpdateUserProfileModel;
import com.alperenikinci.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);


    Auth  toAuth(final RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);


    ActivateResponseDto toActivateResponseDto(final  Auth auth);

    @Mapping(source ="id" ,target ="authId")
    NewCreateUserDto toNewCreateUserDto(final Auth auth);

    List<ActivateResponseDto> toActivateResponseDtos(final List<Auth> authList);
    List<RoleResponseDto> toRoleResponseDtos(final List<Auth> authList);

    RoleResponseDto toRoleResponseDto(final Auth auth);

    LoginResponseDto toLoginResponseDto(final Auth auth);
    @Mapping(source = "id",target = "authId")
    NewCreateUserModel toNewCreateUserModel(final Auth auth);

    Auth toUserAuth(final UpdateUserProfileModel model);
}