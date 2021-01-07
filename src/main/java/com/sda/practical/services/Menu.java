package com.sda.practical.services;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        new Menu().showMenu();
    }

    static void principalMenu() {
        System.out.println("\n ⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Pet Clinic Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸ \n" +
                "1. Employees \n" +
                "2. Pets \n" +
                "3. Examination \n" +
                "4. Exit");
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        principalMenu();
        System.out.print("Please Select an option: ");
        int userOption;

        do {
            userOption = scanner.nextInt();
            scanner.nextLine();
            switch (userOption) {
                case 1:
                    new MenuEmployee().chooseMenuOptions();
                    break;
                case 2:
                    new MenuPet().chooseMenuOptions();
                    break;
                case 3:
                    new MenuExamination().chooseMenuOptions();
                    break;
                case 4:
                    System.exit(0);
                default:
                    new MenuEmployee().invalidOptionMessage();
                    break;
            }
        } while (userOption < 1 || userOption > 4);
    }
}
