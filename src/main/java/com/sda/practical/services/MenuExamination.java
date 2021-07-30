package com.sda.practical.services;

import com.sda.practical.databases.model.Examination;
import com.sda.practical.databases.model.model_fields.ExaminationFields;
import com.sda.practical.databases.repository.EmployeeRepository;
import com.sda.practical.databases.repository.ExaminationRepository;
import com.sda.practical.databases.repository.PetRepository;
import com.sda.practical.views.ConsolePrints;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuExamination {
    Scanner scanner = new Scanner(System.in);
    EmployeeRepository employeeRepository = new EmployeeRepository();
    PetRepository petRepository = new PetRepository();
    ExaminationRepository examinationRepository = new ExaminationRepository();
    ConsolePrints print = new ConsolePrints();

    // start Case 1
    private String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    public void addExamination() {
        Examination examination = new Examination();
        setEmployee(examination);
        setPet(examination);

        String examinationCheckIn = insertField(ExaminationFields.CHECK_IN);
        examination.setCheckIn(convertStringToDate(examinationCheckIn));
        examination.setTreatment(insertField(ExaminationFields.TREATMENT));

        examinationRepository.save(examination);
    }

    private void setEmployee(Examination examination) {
        new MenuEmployee().showAllEmployeesFromDatabase();
        System.out.print("Insert index of the employee you want to add as responsible of examination: ");
        Integer employeeId = scanner.nextInt();
        scanner.nextLine();
        examination.setEmployee(employeeRepository.findById(employeeId));
    }

    private void setPet(Examination examination) {
        new MenuPet().showAllPetsFromDatabase();
        System.out.print("Insert index of the pet you want to add on examination: ");
        Integer petId = scanner.nextInt();
        scanner.nextLine();
        examination.setPet(petRepository.findById(petId));
    }

    public Date convertStringToDate(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return date1;
        } catch (Exception e) {
            print.invalidOptionMessage();
            addExamination();
            return null;
        }
    }
    // end Case 1

    //Start Case 2

    public Examination selectExaminationOfThePetBId() {
        System.out.print("Insert index of the examination: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        return examinationRepository.findById(id);
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
            Integer id = examination.getId();
            examination = examinationRepository.findById(id);
            examination.setCheckOut(checkOut);
            examinationRepository.update(examination);
        }
        print.successfullySavedMessage();
    }

    public void updateMethodFields(Examination examination, String newField) {
        Integer id = examination.getId();
        examination = examinationRepository.findById(id);
        examination.setTreatment(newField);
        examinationRepository.update(examination);
    }

    // end Case 2

    //Start case 3

    public void printExaminationForCheckoutFromDatabase() {
        System.out.println("《《《《《     List of Examinations to check-out   》》》》》");
        List<Examination> examinations = examinationRepository.findAllExaminationsToCheckOut();
        iterateExamination(examinations);
    }

    //End case 3

    //Start Case 4

    public void printExaminationFromDatabase() {
        System.out.println("《《《《《     List of Examinations    》》》》》");
        iterateExamination(examinationRepository.findAll());
    }
    //End Case 4

    public void iterateExamination(List<Examination> examinations) {
        for (Examination examination : examinations) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(examination.getId() + " | " + "Dr. " + examination.getEmployee().getFullName() + " | pet "
                    + examination.getCheckIn() + " | " + examination.getCheckOut() + " | " + examination.getTreatment());
        }
        System.out.println("-----------------------------------------------------------------------");
    }
}
