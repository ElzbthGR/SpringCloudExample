package ru.kpfu.itis.services.services;

import ru.kpfu.itis.services.dto.UserDto;

import java.util.List;

public interface RegistryService {

    void send();

    void sendToSave(UserDto userDto);

    List<UserDto> getAll();

}
