package com.sda.practical.services;


import com.sda.practical.databases.model.User;
import com.sda.practical.databases.repository.AuthorityRepository;
import com.sda.practical.databases.repository.UserRepository;
import com.sda.practical.views.ConsolePrints;

import java.util.Scanner;

public class RegisterPage {
    ConsolePrints print = new ConsolePrints();
    Scanner scanner = new Scanner(System.in);
    AuthorityRepository authorityRepository = new AuthorityRepository();
    UserRepository userRepository = new UserRepository();

    public void saveUser(User user) {
        userRepository.save(user);
    }

    //Create User
    public User registerUser() {
        User user = new User();
        user.setUsername(insertUsername());
        user.setPassword(insertPassword());
        user.setAuthority(authorityRepository.findByRole("USER"));
        saveUser(user);
        return user;
    }

    private String insertPassword() {
        print.insertPasswordMessage();
        return scanner.nextLine();
    }

    protected String insertUsername() {
        print.insertUsernameMessage();
        return scanner.nextLine();
    }
}
