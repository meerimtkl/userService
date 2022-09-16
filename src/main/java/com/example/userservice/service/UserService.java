package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.enums.UserStatus;
import com.example.userservice.model.responses.UserResponse;
import com.example.userservice.model.responses.UserStatusResponse;
import com.example.userservice.repositories.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {

    UserResponse create(UserDto userDto);

    UserDto get(Long id);

    UserStatusResponse updateStatus(Long id, UserStatus userStatus);

    UserDto update(Long id, UserDto userDto);

    UserDto addUser(Long id, MultipartFile file);

    User getById(Long id);
    List<UserDto> getAllUsers(UserStatus status, Timestamp timestamp);


}
