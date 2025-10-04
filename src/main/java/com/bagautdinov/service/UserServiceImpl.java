package com.bagautdinov.service;

import com.bagautdinov.dao.UserDao;
import com.bagautdinov.dao.UserDaoImpl;
import com.bagautdinov.dto.UserDto;
import com.bagautdinov.entity.User;
import com.bagautdinov.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLogin()))
                        .toList();

    }
    @Override
    public boolean registerUser(User user) {
        User existingUser = userDao.getByLogin(user.getLogin());
        if (existingUser != null) {
            return false;
        }

        String hashedPassword = PasswordUtil.encrypt(user.getPassword());
        User userWithHashedPassword = new User(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getLogin(),
                hashedPassword
        );

        userDao.save(userWithHashedPassword);
        return true;
    }

    @Override
    public User loginUser(String login, String password) {
        User user = userDao.getByLogin(login);
        if (user != null) {
            String hashedInputPassword = PasswordUtil.encrypt(password);
            if (hashedInputPassword.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
