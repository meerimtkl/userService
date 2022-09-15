package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.enums.UserStatus;
import com.example.userservice.model.responses.UserResponse;
import com.example.userservice.model.responses.UserStatusResponse;
import com.example.userservice.repositories.UserRepo;
import com.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
//Выполнила Мамыралиева Мээрим
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    //2. Добавление нового пользователя.
    @PostMapping("create")
    public UserResponse save(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }
    //3. Получение информации о пользователе.
    /*
     Этот метод возвращает сущность пользователя с назначенным идентификатором
      @param id - id пользователя в БД
       */

    @GetMapping("get")
    public UserDto get(@RequestParam Long id) {
        return userService.get(id);
    }

    //обновление информацию пользователя по id, userDto
    @PutMapping("update")
    public UserDto update(@RequestParam Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    //4. Изменение статуса пользователя (Online, Offline).
    @PutMapping("updateStatus")
    public UserStatusResponse update(@RequestParam Long id, @RequestParam UserStatus userStatus) {
        return userService.updateStatus(id, userStatus);
    }

         /*  1. Загрузчик.
    o Передаем на сервер файл (картинка аватара JPG).
    o Сохраняем картинку в каталоге на сервере.
    o Ответ сервера - внутренний URI картинки.*/

    @PostMapping("/add/file")
    private UserDto addImageToUser(@RequestParam Long userId, @RequestPart MultipartFile file) {
        return userService.addUser(userId, file);

    }

    /*
      5.Параметры запроса являются необязательными. Параметры используются для фильтрации списка
      пользователей в теле ответа.

       @param status фильтрует пользователей по статусу онлайн/офлайн
       @param id — это временная метка, в случае ее наличия запрос содержит
       только пользователи, чей статус изменился после этой метки времени
      @return Список пользователей
     */
    @GetMapping("/getUsers")
    private List<UserDto> getUsers(@RequestParam(required = false, defaultValue = "ONLINE") UserStatus status,
                                @RequestParam(required = false, value = "id") Long id) {
        return userService.getAllUsers(status, id);
    }


}




