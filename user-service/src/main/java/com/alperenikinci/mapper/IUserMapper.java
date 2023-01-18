package com.alperenikinci.mapper;

import com.alperenikinci.dto.request.NewCreateUserDto;
import com.alperenikinci.dto.request.UpdateRequestDto;
import com.alperenikinci.dto.response.UpdateResponseDto;
import com.alperenikinci.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {


IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);


UserProfile toUserProfile(final NewCreateUserDto dto);

UserProfile toUserProfile(final UpdateRequestDto dto);

UpdateResponseDto toUpdateResponseDto(final UpdateRequestDto dto);

UpdateResponseDto toUpdateResponseDto(final UserProfile userProfile);

}
