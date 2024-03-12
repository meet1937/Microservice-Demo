package com.md.user.service.service;

import com.md.user.service.entities.User;

import java.util.List;

public interface UserService {

    User getUser(String userId);

    List<User> getUsers();

    User saveUser(User user);

    //TODO: delete user
    //TODO: update user
}
