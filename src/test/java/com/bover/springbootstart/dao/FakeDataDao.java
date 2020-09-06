package com.bover.springbootstart.dao;

import com.bover.springbootstart.model.User;

import java.util.*;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;
    static{
        database = new HashMap<>();
        UUID johnUserUid = UUID.randomUUID();
        database.put(johnUserUid, new User(johnUserUid, "John","Johns",
                User.Gender.MALE, 22, "John@johnes@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User getUser(UUID userUid) {
        return database.get(userUid);
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }

    @Override
    public int removeUser(UUID userUid) {
         database.remove(userUid);
         return 1;
    }

    @Override
    public int insertUser(UUID userUid, User user) {
        database.put(userUid, user);
        return 1;
    }
}
