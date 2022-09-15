package com.example.userservice.microservice;

import com.example.userservice.microservice.json.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/*  1. Загрузчик.
o Передаем на сервер файл (картинка аватара JPG).
o Сохраняем картинку в каталоге на сервере.
o Ответ сервера - внутренний URI картинки.*/

@FeignClient(value = "${micro.file-service.name}", url = "${micro.file-service.url}")
public interface FileServiceFeign {
    @PostMapping(value = "/api/v1/file/upload", consumes = "multipart/form-data")
    Response upload(@RequestPart MultipartFile file);
}
