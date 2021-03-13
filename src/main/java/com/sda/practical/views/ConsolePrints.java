package com.sda.practical.views;

public class ConsolePrints {

    public void welcomeMessage() {
        System.out.println("Welcome to our Pet Clinic Online Register! ");
    }

    public void invalidOptionMessage() {
        System.out.println("\n(͠◉_◉᷅ ) \n" +
                "Invalid option, please try again!\n" +
                "Insert a valid option here: ");
    }

    public void goodByeMessage() {
        System.out.println("\n\uD83D\uDC4B\uD83D\uDC4B≧◉ᴥ◉≦ \n" +
                "Goodbye!");
    }

    public void successfullySavedMessage() {
        System.out.println("\nʕ•́ᴥ•̀ʔっ");
        System.out.println("Your update was successfully saved!");
    }

    public void successfullyDeletedMessage() {
        System.out.println("\nʕ•́ᴥ•̀ʔっ");
        System.out.println("Your selection was successfully deleted!");
    }

    public void chooseOptionMessage() {
        System.out.print("Choose a valid option: ");
    }

    public void loginFailedMessage() {
        System.out.println("Login process failed! Try Again!");
    }

    public void loginSuccessfulMessage() {
        System.out.println("\nYou are successfully connected! ");
    }

    public void insertUsernameMessage() {
        System.out.print("\nInsert your username: ");
    }

    public void insertPasswordMessage() {
        System.out.print("\nInsert your password: ");
    }
}
