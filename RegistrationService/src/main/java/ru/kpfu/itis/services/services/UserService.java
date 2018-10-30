package ru.kpfu.itis.services.services;

import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.models.UserModel;

import java.util.List;

public interface UserService {

    void addUser(UserDto userDto);

    List<UserModel> getAll();

}
