package com.sda.practical.services;


import com.sda.practical.databases.model.User;
import com.sda.practical.databases.repository.UserRepository;
import com.sda.practical.views.ConsolePrints;

import java.util.Scanner;

public class LoginPage {
    UserRepository userRepository = new UserRepository();
    Scanner scanner = new Scanner(System.in);
    ConsolePrints prints = new ConsolePrints();

    protected String insertUsername() {
        prints.insertUsernameMessage();
        return scanner.nextLine();
    }

    protected String insertPassword() {
        prints.insertPasswordMessage();
        return scanner.nextLine();
    }

    public User loginProcess() {
        User user = userRepository.logIn(insertUsername(), insertPassword());
        do {
            if (user == null) {
                prints.loginFailedMessage();
                loginProcess();
            } else {
                prints.loginSuccessfulMessage();
                break;
            }
        } while (user != null);
        return user;
    }
}
