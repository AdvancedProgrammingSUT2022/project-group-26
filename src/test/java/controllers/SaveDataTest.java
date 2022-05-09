package controllers;

import models.Game;
import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveDataTest {

    User user1;
    UsersDatabase usersDatabase;

    @BeforeEach
    public void setUp() {
        user1 = new User("ilya", "Ilya1234", "ilya");
        usersDatabase = new UsersDatabase();
        usersDatabase.addUser(user1);
    }

    @Test
    public void saveAndLoadUsersTest() {
        SaveData.saveUserDataBase(usersDatabase);
        UsersDatabase usersDatabase = SaveData.loadAndReturnUserDataBase();

        Assertions.assertEquals("ilya", usersDatabase.getUsers().get(0).getUsername());
    }

    @Test
    public void readFileTest() {
        String username = SaveData.readFile("sdfdfasf");
        Assertions.assertEquals(null, username);
    }
}
