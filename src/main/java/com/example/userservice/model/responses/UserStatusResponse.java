package com.example.userservice.model.responses;

import com.example.userservice.model.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStatusResponse {
    Long id;
    UserStatus newStatus;
    UserStatus oldStatus;

}
