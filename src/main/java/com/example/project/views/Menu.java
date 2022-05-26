package com.example.project.views;

import com.example.project.models.UsersDatabase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    final protected Scanner scanner = new Scanner(System.in);
    protected UsersDatabase usersDatabase;

    public Menu(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    protected void run() {
    }

    protected Matcher getCommandMatcher(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);

        if (matcher.matches())
            return matcher;

        return null;
    }
}