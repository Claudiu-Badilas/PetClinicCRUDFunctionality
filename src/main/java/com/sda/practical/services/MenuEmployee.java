package com.sda.practical.services;

import com.sda.practical.databases.model.Employee;
import com.sda.practical.databases.model.Speciality;
import com.sda.practical.databases.model.model_fields.EmployeeFields;
import com.sda.practical.databases.repository.EmployeeRepository;
import com.sda.practical.databases.repository.SpecialityRepository;
import com.sda.practical.views.ConsolePrints;
import com.sda.practical.views.MenuService;
import com.sda.practical.views.MenuType;

import java.util.Scanner;

public class MenuEmployee {
    Scanner scanner = new Scanner(System.in);
    MenuService menu = new MenuService();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    ConsolePrints print = new ConsolePrints();
    SpecialityRepository specialityRepository = new SpecialityRepository();

    // START CASE 1
    private String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setFullName(insertField(EmployeeFields.FULL_NAME));
        employee.setPhoneNumber(insertField(EmployeeFields.PHONE_NUMBER));
        employee.setAddress(insertField(EmployeeFields.ADDRESS));
        employee.setSpeciality(selectSpeciality());
        return employee;
    }

    private void showAllSpecialitiesFromDatabase(){
        System.out.println("《《《《《     List of Specialities    》》》》》");
        specialityRepository.findAll().forEach(s -> System.out.println("------------------------------------\n" + s));
        System.out.println("------------------------------------");
    }

    private Speciality selectSpeciality(){
        showAllSpecialitiesFromDatabase();
        System.out.println("Select the id corresponding to specialities for setting to the new employee!");
        System.out.print("Insert id here: ");
        int userOption = scanner.nextInt();
        scanner.nextLine();
        return specialityRepository.findById(userOption);
    }

    public void saveEmployeeToDatabase() {
        System.out.println("Complete fields for the new employee");
        Employee employee = createEmployee();
        employeeRepository.save(employee);
    }

        //END CASE 1

       //===================== START CASE 2
    public void chooseFieldToUpdate() {
        showAllEmployeesFromDatabase();
        System.out.print("\nInsert the id of the employee you are looking for: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Employee employee = findEmployeeById(id);
        printEmployee(employee);
        menu.printMenu(MenuType.EMPLOYEE_UPDATE_MENU);
        System.out.print("Select witch field you want to update from selected employee: ");
        int userInsertOption = scanner.nextInt();
        scanner.nextLine();

        switch (userInsertOption) {
            case 1:
                String newFirstName = userUpdateInput(EmployeeFields.FULL_NAME);
                Employee updateEmployeeFirstName = updateMethodFields(employee, newFirstName, EmployeeFields.FULL_NAME);
                printEmployee(updateEmployeeFirstName);
                print.successfullySavedMessage();
                break;
            case 2:
                String newAddress = userUpdateInput(EmployeeFields.ADDRESS);
                Employee updateEmployeeAddress = updateMethodFields(employee, newAddress, EmployeeFields.ADDRESS);
                printEmployee(updateEmployeeAddress);
                print.successfullySavedMessage();
                break;
            case 3:
                //chooseFieldToUpdate();
            default:
                print.invalidOptionMessage();
        }
    }

    public Employee findEmployeeById(Integer id) {
        Employee employee;
        try {
            employee = employeeRepository.findById(id);
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

    public String userUpdateInput(String fieldName) {
        System.out.print("Insert the new " + fieldName + " for the employee: ");
        return scanner.nextLine();
    }

    public Employee updateMethodFields(Employee employee, String newField, String verifyCondition) {
        Employee employeeWithFildUpdated = verifyConditionForUpdate(employee, newField, verifyCondition);
        employeeRepository.update(employeeWithFildUpdated);
        return employeeWithFildUpdated;
    }

    public Employee verifyConditionForUpdate(Employee employee, String newField, String verifyCondition) {
        if (verifyCondition.equals(EmployeeFields.FULL_NAME)) {
            employee.setFullName(newField);
        } else if (verifyCondition.equals(EmployeeFields.ADDRESS)) {
            employee.setAddress(newField);
        }
        return employee;
    }
    //END CASE 2


    // START CASE 3
    public void chooseEmployeeToDelete() {
        showAllEmployeesFromDatabase();
        System.out.print("Insert the id of the employee you are looking for: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Employee employee = findEmployeeById(id);
        printEmployee(employee);
        Character userAnswer = askedForValidation();
        verifyAnswer(userAnswer, id);

    }

    private Character askedForValidation() {
        System.out.println("Are you sure you want to delete this employee? (Y/N)");
        return scanner.nextLine().toUpperCase().charAt(0);
    }

    private void verifyAnswer(Character answer, Integer id) {
        if (answer.equals('Y')) {
            employeeRepository.delete(employeeRepository.findById(id));
            System.out.println("Your selection was deleted with success!");
        } else if (answer.equals('N')) {
            //chooseMenuOptions();
        } else {
            //do something
        }
    }
    // END CASE 3


    // START CASE 4
    public void showAllEmployeesFromDatabase() {
        System.out.println("《《《《《     List of Employees    》》》》》");
        employeeRepository.findAll().forEach(e -> System.out.println("------------------------------------\n" + e));
        System.out.println("------------------------------------");
    }
    // END CASE 4
}
