package com.example.userservice.model.responses;

import com.example.userservice.model.enums.UserStatus;

public class ListUserResponse {
    Long id;

    String uri;
    String userName;

    UserStatus status;
}
