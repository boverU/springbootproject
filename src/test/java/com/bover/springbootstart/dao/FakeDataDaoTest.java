package com.bover.springbootstart.dao;

import com.bover.springbootstart.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllUsers() {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);

        assertThat(user.getAge()).isEqualTo(22);
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Johns");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("John@johnes@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    void selectUserByUserUid() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserByUserUid() {
    }

    @Test
    void insertUser() {
    }
}