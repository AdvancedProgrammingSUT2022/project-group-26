package models;

import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserDatabaseTest {
    private User user1;
    private User user2;
    private UsersDatabase usersDatabase;

    @BeforeEach
    public void setUp(){
        user1 = new User("ilya", "Ilya8456", "ilya");
        user2 = new User("paria", "Ilya8456", "iljgft");
        this.usersDatabase = new UsersDatabase();
        this.usersDatabase.addUser(user1);
        this.usersDatabase.addUser(user2);
    }

    @Test
    public void getUserByUsernameTest(){
        String username = "paria";
        User user = this.usersDatabase.getUserByUsername(username);
        ArrayList<User> users = usersDatabase.getUsers();
        usersDatabase.setUsers(users);
        Assertions.assertEquals(user2, user);
    }

    @Test
    public void nullGetUserByUsernameTest(){
        String username = "paia";
        User user = this.usersDatabase.getUserByUsername(username);
        Assertions.assertEquals(null, user);
    }

    @Test
    public void getUserByNicknameTest(){
        String nickname = "ilya";
        User user = this.usersDatabase.getUserByNickname(nickname);
        Assertions.assertEquals(user1, user);
    }

    @Test
    public void nullGetUserByNicknameTest(){
        String nickname = "paia";
        usersDatabase.removeUser(user2);
        User user = this.usersDatabase.getUserByNickname(nickname);
        Assertions.assertEquals(null, user);
    }
}
