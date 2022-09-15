package com.example.userservice.model;

import com.example.userservice.model.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String uri;
    @Column(unique = true)
    String userName;
    @Column(unique = true)
    String email;
    @Enumerated(value = EnumType.STRING)
    UserStatus status;
    //    @JsonFormat(pattern="yyyy-MM-dd")
//    LocalDate userTimestamp;
    @Column(name = "status_timestamp")
    Timestamp statusTimestamp;

}
