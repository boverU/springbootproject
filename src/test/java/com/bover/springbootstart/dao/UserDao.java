package com.bover.springbootstart.dao;

import com.bover.springbootstart.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> getAllUsers();

    User getUser(UUID userUid);

    int updateUser(User user);

    int removeUser(UUID userUid);

    int insertUser(UUID userUid, User user);
}
