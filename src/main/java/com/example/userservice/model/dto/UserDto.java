package com.example.userservice.model.dto;

import com.example.userservice.model.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String uri;
    String name;
    String email;
    UserStatus status;
    Timestamp statusTimestamp;
}
