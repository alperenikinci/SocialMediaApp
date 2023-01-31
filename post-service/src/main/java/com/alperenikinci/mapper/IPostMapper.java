package com.alperenikinci.mapper;

import com.alperenikinci.dto.request.PostCreateRequestDto;
import com.alperenikinci.dto.response.PostCreateResponseDto;
import com.alperenikinci.repository.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IPostMapper {


IPostMapper INSTANCE= Mappers.getMapper(IPostMapper.class);

PostCreateResponseDto toPostCreateResponseDto(final Post post);

Post toPost(final PostCreateRequestDto  dto);


}
