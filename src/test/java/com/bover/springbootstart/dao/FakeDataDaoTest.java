package com.bover.springbootstart.dao;

import com.bover.springbootstart.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
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
    void shouldNotSelectUserByRandomUserUid() {

    Optional<User> user =  fakeDataDao.selectUserByUserUid(UUID.randomUUID());
    assertThat(user.isPresent()).isFalse();
    }

    @Test
    void shouldSelectUserByUserUid() {

        UUID annaUserId = UUID.randomUUID();
        User anna = new User(annaUserId, "Anna", "Montana",
                    User.Gender.FEMALE, 25, "anna@gmail.com");
        fakeDataDao.insertUser(annaUserId, anna);

//        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> annaOptional = fakeDataDao.selectUserByUserUid(annaUserId);
        assertThat(annaOptional.isPresent()).isTrue();
        assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
    }

    @Test
    void updateUser() {
        var johnUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        User newJohn = new User(johnUid, "Anna", "Montana",
                User.Gender.FEMALE, 25, "anna@gmail.com");
        fakeDataDao.updateUser(newJohn);
        var optionalUser = fakeDataDao.selectUserByUserUid(johnUid);
        assertThat(optionalUser.isPresent()).isTrue();

        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(optionalUser.get()).isEqualToComparingFieldByField(newJohn);
    }

    @Test
    void shouldDeleteUserByUserUid() {
        var johnUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        fakeDataDao.deleteUserByUserUid(johnUid);
        assertThat(fakeDataDao.selectUserByUserUid(johnUid).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();
    }

    @Test
    void shouldInsertUser() {
        var uuid = UUID.randomUUID();
        User newJohn = new User(uuid, "John", "Glen",
                User.Gender.MALE, 27, "anna@gmail.com");
        fakeDataDao.insertUser(uuid, newJohn);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
        assertThat((fakeDataDao.selectUserByUserUid(uuid)).get()).isEqualToComparingFieldByField(newJohn);
    }
}