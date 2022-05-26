package controllers;

import com.example.project.controllers.SaveData;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveDataTest {

    private User user1;
    private UsersDatabase usersDatabase;

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
