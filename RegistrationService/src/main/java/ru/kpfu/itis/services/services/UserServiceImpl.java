package ru.kpfu.itis.services.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.services.Reciever;
import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.models.UserModel;
import ru.kpfu.itis.services.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(UserDto userDto) {
        UserModel newUser = new UserModel(userDto.getName(), userDto.getUrl());
        this.userRepository.save(newUser);

        logger.info("user saved");
    }

    @Override
    public List<UserModel> getAll() {
        return this.userRepository.findAll();
    }

}
