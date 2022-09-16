package com.example.userservice.service.impl;

import com.example.userservice.mapper.UserMapper;
import com.example.userservice.microservice.FileServiceFeign;
import com.example.userservice.microservice.json.Response;
import com.example.userservice.model.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.enums.UserStatus;
import com.example.userservice.model.responses.ListUserResponse;
import com.example.userservice.model.responses.UserResponse;
import com.example.userservice.model.responses.UserStatusResponse;
import com.example.userservice.repositories.UserRepo;
import com.example.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final FileServiceFeign fileServiceFeign;


    public UserServiceImpl(UserRepo userRepo, FileServiceFeign fileServiceFeign) {
        this.userRepo = userRepo;
        this.fileServiceFeign = fileServiceFeign;
        this.userMapper = UserMapper.INSTANCE;
    }


    @Override
    public UserResponse create(UserDto userDto) {

        System.out.println(userDto.getName());
        User user = userMapper.toEntity(userDto);
        System.out.println(user.getUserName());


        user = userRepo.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        return userResponse;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User user = userRepo.findById(id).orElseThrow();

        user.setUserName(userDto.getName());
        user.setStatus(userDto.getStatus());
        user.setUri(userDto.getUri());

        Timestamp updateTime = new Timestamp((new Date()).getTime());
        user.setStatusTimestamp(updateTime);

        userRepo.save(user);


        UserDto userDtoUpdated = userMapper.toDto(user);
        return userDtoUpdated;

    }

    @Override
    public UserDto get(Long id) {
        User user = userRepo.findById(id).orElseThrow();

        return userMapper.toDto(user);
    }
    public List<UserDto> getAllUsers(UserStatus status, Timestamp timestamp) {
        ListUserResponse userList1=new ListUserResponse();
        if (timestamp != null) {

            if (status != null) {

                List<User> userList=userRepo.findByStatusAndStatusTimestampAfter(status, timestamp);

                List<UserDto> userDtoList=userMapper.toDtos(userList);
                return userDtoList;
            }
            List<User> userList=userRepo.findByStatusTimestampAfter(timestamp);

            List<UserDto> userDtoList=userMapper.toDtos(userList);
            return userDtoList;

        } else if (status != null) {

            List<User> userList= userRepo.findByStatus(status);
            List<UserDto> userDtoList=userMapper.toDtos(userList);
            return userDtoList;
        }
        List<User> userList=userRepo.findAll();

        List<UserDto> userDtoList=userMapper.toDtos(userList);
        return userDtoList;
    }
    @Override
    public UserStatusResponse updateStatus(Long id, UserStatus userStatus) {
        Timestamp updateTime = new Timestamp((new Date()).getTime());
        User user = userRepo.findById(id).orElseThrow();


        UserStatusResponse userStatusResponse = new UserStatusResponse();
        userStatusResponse.setId(id);
        userStatusResponse.setOldStatus(user.getStatus());

        user.setStatus(userStatus);
        user.setStatusTimestamp(updateTime);
        userRepo.save(user);

        userStatusResponse.setNewStatus(user.getStatus());

        return userStatusResponse;
    }

    // o Ответ сервера - внутренний URI картинки.*/
    @Override
    public UserDto addUser(Long id, MultipartFile file) {
        User user = getById(id);
        Response response = fileServiceFeign.upload(file);
        //User user=userMapper.toEntity(userDto);

        user.setUri(response.getDownloadUri());
        userRepo.save(user);
        UserDto userDtoUpdated=userMapper.toDto(user);
        return userDtoUpdated;
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

   /* @Override
    public List<User> getAllUsers(UserStatus userStatus,LocalDate date) {
        List<User> userDtoList=userRepo.findUsersByStatusEqualsAndUserTimestampEquals(userStatus,date);

        return userDtoList;
    }
*/

}
