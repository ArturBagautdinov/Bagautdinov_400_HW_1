package com.bagautdinov.service;

import com.bagautdinov.dto.UserDto;
import com.bagautdinov.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    boolean registerUser(User user);
    User loginUser(String login, String password);
}
