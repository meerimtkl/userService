package com.example.userservice.repositories;

import com.example.userservice.model.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByStatusAndStatusTimestampAfter(UserStatus status, Timestamp statusTimestamp);

    List<User> findByStatus(UserStatus online);

    List<User> findByStatusTimestampAfter(Timestamp statusTimestamp);

}
