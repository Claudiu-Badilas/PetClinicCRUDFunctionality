package com.sda.practical.services;

import com.sda.practical.databases.dao.EmployeeDao;
import com.sda.practical.databases.entity.Employee;
import com.sda.practical.views.EmployeeFields;
import com.sda.practical.views.MenuServices;

import java.util.List;
import java.util.Scanner;

public class MenuEmployee implements MenuServices {
    EmployeeDao dao = new EmployeeDao();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        System.out.println("" +
                "1. Add a new employee \n" +
                "2. Update an existing employee \n" +
                "3. Delete an employee \n" +
                "4. View all employees\n" +
                "5. Return to principal menu!\n");
    }


    @Override
    public void chooseMenuOptions() {
        System.out.println("\n ⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Employee Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸ \n");
        showMenu();
        System.out.print("Please choose a valid option: ");
        Integer userOption;
        do {
            userOption = scanner.nextInt();
            scanner.nextLine();
            switch (userOption) {
                case 1:
                    addEmployeeToDatabase();
                    chooseAnotherMenuOption();
                    break;
                case 2:
                    chooseFieldToUpdate();
                    chooseAnotherMenuOption();
                    break;
                case 3:
                    showAllEmployeesFromDatabase();
                    chooseEmployeeToDelete();
                    chooseAnotherMenuOption();
                    break;
                case 4:
                    showAllEmployeesFromDatabase();
                    chooseAnotherMenuOption();
                    break;
                case 5:
                    new Menu().showMenu();
                    break;
                default:
                    invalidOptionMessage();
            }
        } while (userOption < 1 || userOption > 5);
    }



    // START CASE 4

    public void showAllEmployeesFromDatabase() {
        System.out.println("《《《《《     List of Employees    》》》》》");
        List<Employee> employees = dao.getEmployees();
        for (Employee employee : employees) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(employee);
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    // END CASE 4

    // START CASE 3
    public void chooseEmployeeToDelete() {
        System.out.print("Insert the first name of the employee you are looking for: ");
        String firstName = scanner.nextLine();
        Employee employee = serchEmployeeByFirstName(firstName);
        printEmployee(employee);
        Character userAnswer = askedForValidation();
        verifyAnswer(userAnswer, employee);

    }

    public Character askedForValidation() {
        System.out.println("Are you sure you want to delete this employee? (Y/N)");
        Character userAnswer = scanner.nextLine().toUpperCase().charAt(0);
        return userAnswer;
    }

    public void verifyAnswer(Character answer, Employee employee) {
        if (answer.equals('Y')) {
            Integer id = employee.getEmployeeId();
            employee = dao.getEmployee(id);
            dao.deleteEmployee(employee);
            System.out.println("Your selection was deleted with succes!");
        } else if (answer.equals('N')) {
            chooseMenuOptions();
        } else {
            //do something
        }
    }

    // END CASE 3

//==================================================================================================================

    // START CASE 2
    public void chooseFieldToUpdate() {
        showAllEmployeesFromDatabase();
        System.out.print("\nInsert the first name of the employee you are looking for: ");
        String firstName = scanner.nextLine();
        Employee employee = serchEmployeeByFirstName(firstName);
        printEmployee(employee);
        showMenuForUpdate();
        System.out.print("Select witch field you want to update from selected employee: ");
        int userInsertOption = scanner.nextInt();
        scanner.nextLine();

        switch (userInsertOption) {
            case 1:
                String newFirstName = userUpdateInput(EmployeeFields.FIRSTNAME.getName());
                Employee updateEmployeeFirstName = updateMethodFields(employee, newFirstName, EmployeeFields.FIRSTNAME.getName());
                printEmployee(updateEmployeeFirstName);
                succesfullySaved();
                break;
            case 2:
                String newLastName = userUpdateInput(EmployeeFields.LASTNAME.getName());
                Employee updateEmployeeLastName = updateMethodFields(employee, newLastName, EmployeeFields.LASTNAME.getName());
                printEmployee(updateEmployeeLastName);
                succesfullySaved();
                break;
            case 3:
                String newAddress = userUpdateInput(EmployeeFields.ADDRESS.getName());
                Employee updateEmployeeAddress = updateMethodFields(employee, newAddress, EmployeeFields.ADDRESS.getName());
                printEmployee(updateEmployeeAddress);
                succesfullySaved();
                break;
            case 4:
                String newSpeciality = userUpdateInput(EmployeeFields.SPECIALITY.getName());
                Employee updateEmployeeSpeciality = updateMethodFields(employee, newSpeciality, EmployeeFields.SPECIALITY.getName());
                printEmployee(updateEmployeeSpeciality);
                succesfullySaved();
                break;
            case 5:
                chooseFieldToUpdate();
            default:
                invalidOptionMessage();
        }
    }

    public Employee serchEmployeeByFirstName(String firstName) {
        Employee employee;
        try {
            employee = dao.getEmployeeByFirstName(firstName);
            return employee;
        } catch (Exception e) {
            System.out.println("This employee doesn't exist in our database!");
            e.printStackTrace();
            return null;
        }
    }

    public void printEmployee(Employee employee) {
        System.out.println("========================================================");
        System.out.println(employee);
        System.out.println("========================================================");
    }

    public void showMenuForUpdate() {
        System.out.println(
                "1. Update first name  \n" +
                        "2. Update last name  \n" +
                        "3. Update address  \n" +
                        "4. Update speciality  \n" +
                        "5. Return!");
        System.out.println();
    }

    public String userUpdateInput(String fieldName) {
        System.out.print("Insert the new " + fieldName + " for the employee: ");
        return scanner.nextLine();
    }

    public Employee updateMethodFields(Employee employee, String newField, String verifyCondition) {
        Employee employeeWithFildUpdated = verifyConditionForUpdate(employee, newField, verifyCondition);
        dao.updateEmployee(employeeWithFildUpdated);
        return employeeWithFildUpdated;
    }

    public Employee verifyConditionForUpdate(Employee employee, String newField, String verifyCondition) {
        if (verifyCondition.equals(EmployeeFields.FIRSTNAME.getName())) {
            getEmployeeById(employee);
            employee.setFirstName(newField);
        } else if (verifyCondition.equals(EmployeeFields.LASTNAME.getName())) {
            getEmployeeById(employee);
            employee.setLastName(newField);
        } else if (verifyCondition.equals(EmployeeFields.ADDRESS.getName())) {
            getEmployeeById(employee);
            employee.setAddress(newField);
        } else if (verifyCondition.equals(EmployeeFields.SPECIALITY.getName())) {
            getEmployeeById(employee);
            employee.setSpeciality(newField);
        }
        return employee;
    }

    public Employee getEmployeeById(Employee employee) {
        Integer id = employee.getEmployeeId();
        return dao.getEmployee(id);
    }

    public void succesfullySaved() {
        System.out.println("\nʕ•́ᴥ•̀ʔっ");
        System.out.println("Your update was succesfully saved!");
    }

    //END CASE 2

//====================================================================================================================

    // START CASE 1
    public String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    public Employee createEmployeeByUser() {
        String firstName = insertField(EmployeeFields.FIRSTNAME.getName());
        String lastName = insertField(EmployeeFields.LASTNAME.getName());
        String address = insertField(EmployeeFields.ADDRESS.getName());
        String speciality = insertField(EmployeeFields.SPECIALITY.getName());
        return new Employee(firstName, lastName, address, speciality);
    }

    public void addEmployeeToDatabase() {
        System.out.println("Complete fields for the new employee");
        Employee employee = createEmployeeByUser();
        dao.addEmployee(employee);
    }
    //END CASE 1


    public void goodByeMessage() {
        System.out.println("\n\uD83D\uDC4B≧◉ᴥ◉≦ \n" +
                "Goodbye!");
    }

    public void invalidOptionMessage() {
        System.out.println("\n(͠◉_◉᷅ ) \n" +
                "Invalid option, please try again!\n" +
                "Insert a valid option here: ");
    }

    public void chooseAnotherMenuOption() {
        System.out.print("\nDo you want to choose another option menu? (y/n) \n" +
                "Insert option here: ");
        char yesOrNo;
        do {
            yesOrNo = scanner.nextLine().toLowerCase().charAt(0);
            if (yesOrNo == 'y') {
                chooseMenuOptions();
            } else if (yesOrNo == 'n') {
                goodByeMessage();
                System.exit(0);
            } else {
                invalidOptionMessage();
            }
        } while (yesOrNo != 'y' && yesOrNo != 'n');
    }
}
