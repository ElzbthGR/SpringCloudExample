package ru.kpfu.itis.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.models.UserModel;
import ru.kpfu.itis.services.services.UserService;

import java.util.List;

@Controller
public class RegistrationController {

    List<UserDto> usersDto;
    List<UserModel> users;

    @Autowired
    UserService service;

    @GetMapping("/get_all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        users = service.getAll();
//        for(UserModel u: users) {
//            usersDto.add(UserDto.builder()
//            .name(u.getName())
//            .url(u.getUrl())
//            .build());
//        }
        return ResponseEntity.ok(users);
    }

}
