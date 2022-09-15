package com.example.userservice.mapper;

import com.example.userservice.model.User;
import com.example.userservice.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userName", target = "name")
    UserDto toDto(User user);

    @Mapping(source = "name", target = "userName")
    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> userList);


}
