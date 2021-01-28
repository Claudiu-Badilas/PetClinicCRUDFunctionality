package com.sda.practical.services;

import com.sda.practical.databases.dao.EmployeeDao;
import com.sda.practical.databases.dao.ExaminationDao;
import com.sda.practical.databases.dao.PetDao;
import com.sda.practical.databases.entity.*;
import com.sda.practical.views.ExaminationFields;
import com.sda.practical.views.MenuServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuExamination implements MenuServices {
    Scanner scanner = new Scanner(System.in);
    ExaminationDao dao = new ExaminationDao();

    @Override
    public void showMenu() {
        System.out.println("" +
                "1. Check-in a new examination for a pet \n" +
                "2. Update a treatment status of the pet  \n" +
                "3. Check-out an existing examination for a pet \n" +
                "4. View all examinations \n" +
                "5. Return to principal menu!");
    }

    @Override
    public void chooseMenuOptions() {
        System.out.println("\n ⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Examinations Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸ \n");
        showMenu();
        System.out.print("Please choose a valid option: ");
        Integer userOption;
        do {
            userOption = scanner.nextInt();
            scanner.nextLine();
            switch (userOption) {
                case 1:
                    createExaminationByUser();
                    succesfullySaved();
                    chooseAnotherMenuOption();
                    break;
                case 2:
                    printExaminationFromDatabase();
                    chooseFieldToUpdate(ExaminationFields.TREATMENT);
                    chooseAnotherMenuOption();
                    break;
                case 3:
                    printExaminationForCheckoutFromDatabase();
                    chooseFieldToUpdate(ExaminationFields.CHECK_OUT);
                    chooseAnotherMenuOption();
                    break;
                case 4:
                    printExaminationFromDatabase();
                    chooseAnotherMenuOption();
                    break;
                case 5:
                    new Menu().showMenu();
                default:
                    invalidOptionMessage();
            }
        } while (userOption < 1 || userOption > 5);
    }
    //Start Case 4

    public void printExaminationFromDatabase() {
        System.out.println("《《《《《     List of Examinations    》》》》》");
        List<Examination> examinations = dao.getExaminations();
        iterateExamination(examinations);
    }
    //End Case 4

    public void iterateExamination(List<Examination> examinations) {
        for (Examination examination : examinations) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(examination.getExamId() + " | " + "Dr. " + examination.getEmployee().getFirstName() + " " +
                    examination.getEmployee().getLastName() + " | pet " +
                    examination.getPet().getPetName() + " | " + examination.getCheckIn() + " | " + examination.getCheckOut() + " | " + examination.getTreatment());
            System.out.println("-----------------------------------------------------------------------");
        }
    }
    //Start case 3

    public void printExaminationForCheckoutFromDatabase() {
        System.out.println("《《《《《     List of Examinations to check-out   》》》》》");
        List<Examination> examinations = dao.getExaminationsToCheckOut();
        iterateExamination(examinations);
    }


    //End case 3

    //Start Case 2


    public Examination selectExaminationOfThePetBId() {
        System.out.print("Insert index of the examination: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        return dao.getExamination(id);
    }

    public void chooseFieldToUpdate(String fieldName) {
        Examination examination = selectExaminationOfThePetBId();
        System.out.println();
        if (fieldName.equals(ExaminationFields.TREATMENT)) {
            String newTreatment = insertField(ExaminationFields.TREATMENT);
            updateMethodFields(examination, newTreatment);
        } else if (fieldName.equals(ExaminationFields.CHECK_OUT)) {
            String date = insertField(ExaminationFields.CHECK_OUT);
            Date checkOut = convertStringToDate(date);
            Integer id = examination.getExamId();
            examination = dao.getExamination(id);
            examination.setCheckOut(checkOut);
            dao.updateExamination(examination);
        }
        succesfullySaved();
    }

    public Examination updateMethodFields(Examination examination, String newField) {
        Integer id = examination.getExamId();
        examination = dao.getExamination(id);
        examination.setTreatment(newField);
        dao.updateExamination(examination);
        return examination;
    }

    public Examination verifyConditionForUpdate(Examination examination, String newField) {
        Integer id = examination.getExamId();
        examination = dao.getExamination(id);
        examination.setTreatment(newField);
        return examination;
    }
    // end Case 2

    // start Case 1
    public String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    public void createExaminationByUser() {
        String examinationCheckIn = insertField(ExaminationFields.CHECK_IN);
        Date checkInDate = convertStringToDate(examinationCheckIn);
        String petTreatmentDetails = insertField(ExaminationFields.TREATMENT);

        Examination examination = new Examination(checkInDate, null, petTreatmentDetails);

        setEmployee(examination);
        setPet(examination);
        dao.addExamination(examination);
    }

    public Examination setEmployee(Examination examination) {
        new MenuEmployee().showAllEmployeesFromDatabase();
        System.out.print("Insert index of the employee you want to add as responsible of examination: ");
        Integer employeeId = scanner.nextInt();
        scanner.nextLine();
        examination.setEmployee(new EmployeeDao().getEmployee(employeeId));
        return examination;
    }

    public Examination setPet(Examination examination) {
        new MenuPet().showAllPetsFromDatabase();
        System.out.print("Insert index of the pet you want to add on examination: ");
        Integer petId = scanner.nextInt();
        scanner.nextLine();
        examination.setPet(new PetDao().getPet(petId));
        return examination;
    }

    public Date convertStringToDate(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return date1;
        } catch (Exception e) {
            invalidOptionMessage();
            createExaminationByUser();
            return null;
        }
    }
    // end Case 1

    public void succesfullySaved() {
        System.out.println("\nʕ•́ᴥ•̀ʔっ \n" +
                "Your update was succesfully saved!");
    }

    public void goodByeMessage() {
        System.out.println("\n \uD83D\uDC4B≧◉ᴥ◉≦ \n" +
                "Goodbye!");
    }

    public void chooseAnotherMenuOption() {
        System.out.print("\nDo you want to choose another option menu? (Y/N) \n" +
                "Insert option here: ");
        char yesOrNo;
        do {
            yesOrNo = scanner.nextLine().toUpperCase().charAt(0);
            if (yesOrNo == 'Y') {
                chooseMenuOptions();
            } else if (yesOrNo == 'N') {
                goodByeMessage();
                System.exit(0);
            } else {
                invalidOptionMessage();
            }
        } while (yesOrNo != 'Y' && yesOrNo != 'Y');
    }

    public void invalidOptionMessage() {
        System.out.println("\n(͠◉_◉᷅ ) \n" +
                "Invalid option, please try again!\n" +
                "Insert a valid option here: ");
    }
}
