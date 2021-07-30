package com.sda.practical.services;

import com.sda.practical.databases.model.User;
import com.sda.practical.databases.repository.UserRepository;
import com.sda.practical.views.ConsolePrints;
import com.sda.practical.views.MenuService;
import com.sda.practical.views.MenuType;
import com.sda.practical.views.Role;

import java.util.Scanner;

public class Menu {

    MenuService menus = new MenuService();
    ConsolePrints print = new ConsolePrints();
    Scanner scanner = new Scanner(System.in);
    UserRepository userRepository = new UserRepository();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    MenuEmployee menuEmployee = new MenuEmployee();
    MenuPet menuPet = new MenuPet();
    MenuExamination menuExamination = new MenuExamination();


    public void showMenu() {
        int menuId = MenuType.LOGIN_MENU.getMenuId();
        User user = null;
        print.welcomeMessage();
        menus.printMenu(MenuType.LOGIN_MENU);
        int userInput;
        do {
            print.chooseOptionMessage();
            userInput = scanner.nextInt();
            scanner.nextLine();
            if (menuId == MenuType.LOGIN_MENU.getMenuId()) {
                if (userInput == 1) {
                    user = loginPage.loginProcess();
                    menus.printMenu(MenuType.MAIN_MENU);
                    menuId = MenuType.MAIN_MENU.getMenuId();
                } else if (userInput == 2) {
                    user = registerPage.registerUser();
                    menus.printMenu(MenuType.MAIN_MENU);
                    menuId = MenuType.MAIN_MENU.getMenuId();
                } else if (userInput == 3) {
                    print.goodByeMessage();
                    break;
                }
            } else if (menuId == MenuType.MAIN_MENU.getMenuId()) {
                if (userInput == 1) {
                    menus.printMenu(MenuType.EMPLOYEE_MENU);
                    menuId = MenuType.EMPLOYEE_MENU.getMenuId();
                } else if (userInput == 2) {
                    menus.printMenu(MenuType.PET_MENU);
                    menuId = MenuType.PET_MENU.getMenuId();
                } else if (userInput == 3) {
                    menus.printMenu(MenuType.EXAMINATION_MENU);
                    menuId = MenuType.EXAMINATION_MENU.getMenuId();
                } else if (userInput == 4) {
                    print.goodByeMessage();
                    break;
                } else if (userInput == 5 && user.getAuthority().getRole() == Role.ADMIN) {
                    menus.printMenu(MenuType.USER_MENU);
                    menuId = MenuType.USER_MENU.getMenuId();
                }
            } else if (menuId == MenuType.EMPLOYEE_MENU.getMenuId()) {
                if (userInput == 1) {
                    menuEmployee.saveEmployeeToDatabase();
                    print.successfullySavedMessage();
                    chooseAnotherMenuOption(MenuType.EMPLOYEE_MENU);
                } else if (userInput == 2) {
                    menus.printMenu(MenuType.EMPLOYEE_UPDATE_MENU);
                    //menuId = MenuType.EMPLOYEE_UPDATE_MENU.getMenuId();
                    menuEmployee.chooseFieldToUpdate();
                    chooseAnotherMenuOption(MenuType.EMPLOYEE_MENU);
                } else if (userInput == 3) {
                    menuEmployee.chooseEmployeeToDelete();
                    chooseAnotherMenuOption(MenuType.EMPLOYEE_MENU);
                } else if (userInput == 4) {
                    menuEmployee.showAllEmployeesFromDatabase();
                    chooseAnotherMenuOption(MenuType.EMPLOYEE_MENU);
                } else if (userInput == 5) {
                    menus.printMenu(MenuType.MAIN_MENU);
                    menuId = MenuType.MAIN_MENU.getMenuId();
                }
            } else if (menuId == MenuType.PET_MENU.getMenuId()) {
                if (userInput == 1) {
                    menuPet.savePet();
                    print.successfullySavedMessage();
                    chooseAnotherMenuOption(MenuType.PET_MENU);
                } else if (userInput == 2) {
                    menus.printMenu(MenuType.PET_UPDATE_MENU);
                    menuId = MenuType.PET_UPDATE_MENU.getMenuId();
//TODO update employee

                } else if (userInput == 3) {
                    menuPet.choosePetToDelete();
                    chooseAnotherMenuOption(MenuType.PET_MENU);
                } else if (userInput == 4) {
                    menuPet.showAllPetsFromDatabase();
                    chooseAnotherMenuOption(MenuType.PET_MENU);
                } else if (userInput == 5) {
                    menus.printMenu(MenuType.MAIN_MENU);
                    menuId = MenuType.MAIN_MENU.getMenuId();
                }
            } else if (menuId == MenuType.EXAMINATION_MENU.getMenuId()) {
                if (userInput == 1) {
                    menuExamination.addExamination();
                    print.successfullySavedMessage();
                    chooseAnotherMenuOption(MenuType.EXAMINATION_MENU);
                } else if (userInput == 2) {
                    //menus.printMenu(MenuType.PET_UPDATE_MENU);
                    //menuId = MenuType.PET_UPDATE_MENU.getMenuId();
//TODO update examination

                } else if (userInput == 3) {
                    menuExamination.printExaminationForCheckoutFromDatabase();
                    chooseAnotherMenuOption(MenuType.EXAMINATION_MENU);
                } else if (userInput == 4) {
                    menuExamination.printExaminationFromDatabase();
                    chooseAnotherMenuOption(MenuType.EXAMINATION_MENU);
                } else if (userInput == 5) {
                    menus.printMenu(MenuType.MAIN_MENU);
                    menuId = MenuType.MAIN_MENU.getMenuId();
                }
            } else if (menuId == MenuType.USER_MENU.getMenuId()) {
                if (menuId == 1) {

                }

            } else {
                print.invalidOptionMessage();
            }
        } while (userInput > 1 || userInput < 6);
    }

    public void chooseAnotherMenuOption(MenuType menuType) {
        System.out.print("\nDo you want to choose another option menu? (y/n) \n" +
                "Insert option here: ");
        char yesOrNo;
        do {
            yesOrNo = scanner.nextLine().toLowerCase().charAt(0);
            if (yesOrNo == 'y') {
                menus.printMenu(menuType);
            } else if (yesOrNo == 'n') {
                print.goodByeMessage();
                System.exit(0);
            } else {
                print.invalidOptionMessage();
            }
        } while (yesOrNo != 'y' && yesOrNo != 'n');
    }
}
