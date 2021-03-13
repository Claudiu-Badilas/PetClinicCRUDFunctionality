package com.sda.practical.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuService {
    HashMap<MenuType, List<String>> menus;

    public MenuService() {
        menus = new HashMap<>();

        List<String> loginMenu = new ArrayList<>();
        loginMenu.add("1. Login");
        loginMenu.add("2. Register");
        loginMenu.add("3. Exit");
        this.menus.put(MenuType.LOGIN_MENU, loginMenu);

        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Pet Clinic Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸");
        mainMenu.add("1. Employees");
        mainMenu.add("2. Pets");
        mainMenu.add("3. Examination");
        mainMenu.add("4. Exit");
        mainMenu.add("5. Users Details");
        this.menus.put(MenuType.MAIN_MENU, mainMenu);

        List<String> employeeMenu = new ArrayList<>();
        employeeMenu.add("⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Employee Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸");
        employeeMenu.add("1. Add a new employee");
        employeeMenu.add("2. Update an existing employee");
        employeeMenu.add("3. Delete an employee");
        employeeMenu.add("4. View all employees");
        employeeMenu.add("5. Return to main menu!");
        this.menus.put(MenuType.EMPLOYEE_MENU, employeeMenu);

        List<String> employeeUpdateMenu = new ArrayList<>();
        employeeUpdateMenu.add("1. Update full name");
        employeeUpdateMenu.add("2. Update address");
        employeeUpdateMenu.add("3. Return!");
        this.menus.put(MenuType.EMPLOYEE_UPDATE_MENU, employeeUpdateMenu);

        List<String> petMenu = new ArrayList<>();
        petMenu.add("⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Pets Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸");
        petMenu.add("1. Add a new pet");
        petMenu.add("2. Update an existing pet");
        petMenu.add("3. Delete an pet");
        petMenu.add("4. View all pets");
        petMenu.add("5. Return to main menu!");
        this.menus.put(MenuType.PET_MENU, petMenu);

        List<String> petUpdateMenu = new ArrayList<>();
        petUpdateMenu.add("1. Update pet status treatment");
        petUpdateMenu.add("2. Update pet age");
        petUpdateMenu.add("3. Return to main menu!");
        this.menus.put(MenuType.PET_UPDATE_MENU, petUpdateMenu);

        List<String> examinationMenu = new ArrayList<>();
        examinationMenu.add("⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Examinations Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸");
        examinationMenu.add("1. Check-in a new examination for a pet");
        examinationMenu.add("2. Update a treatment status of the pet");
        examinationMenu.add("3. Check-out an existing examination for a pet");
        examinationMenu.add("4. View all examinations");
        examinationMenu.add("5. Return to main menu!");
        this.menus.put(MenuType.EXAMINATION_MENU, examinationMenu);


        List<String> userMenu = new ArrayList<>();
        userMenu.add("⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Users Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸");
        userMenu.add("1. Add a new ADMIN user");
        userMenu.add("2. Add a new USER user");
        userMenu.add("3. Delete an existing user");
        userMenu.add("4. View all users");
        userMenu.add("5. Return to main menu!");
        this.menus.put(MenuType.USER_MENU, userMenu);



    }

    public void printMenu(MenuType menuType) {
        this.menus.get(menuType).forEach(System.out::println);
    }
}
