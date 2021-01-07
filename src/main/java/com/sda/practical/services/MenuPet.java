package com.sda.practical.services;

import com.sda.practical.databases.dao.PetDao;
import com.sda.practical.databases.entity.Pet;
import com.sda.practical.views.PetFields;
import com.sda.practical.views.MenuServices;

import java.util.List;
import java.util.Scanner;

public class MenuPet implements MenuServices {

    Scanner scanner = new Scanner(System.in);
    PetDao dao = new PetDao();

    @Override
    public void showMenu() {
        System.out.println("1. Add a new pet \n" +
                "2. Update an existing pet \n" +
                "3. Delete an pet \n" +
                "4. View all pets\n" +
                "5. Return to principal menu!");
    }

    @Override
    public void chooseMenuOptions() {
        System.out.println("\n ⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷     Welcome to Pets Menu     ⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸ \n");
        showMenu();
        System.out.print("Please choose a valid option: ");
        Integer userOption;
        do {
            userOption = scanner.nextInt();
            scanner.nextLine();
            switch (userOption) {
                case 1:
                    addPet();
                    succesfullySaved();
                    chooseAnotherMenuOption();
                    break;
                case 2:
                    chooseFieldToUpdate();
                    succesfullySaved();
                    chooseAnotherMenuOption();
                    break;
                case 3:
                    showAllPetsFromDatabase();
                    choosePetToDelete();
                    succesfullySaved();
                    chooseAnotherMenuOption();
                    break;
                case 4:
                    showAllPetsFromDatabase();
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

    //Case 4 start
    public void showAllPetsFromDatabase() {
        System.out.println("《《《《《     List of Pets    》》》》》");
        List<Pet> pets = dao.getPets();
        for (Pet pet : pets) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(pet);
            System.out.println("-----------------------------------------------------------------------");
        }
    }
    //Case 4 end


    //Case 3 start
    public void choosePetToDelete() {
        Pet pet = selectPetById();
        Character userAnswer = askedForValidation();
        verifyAnswer(userAnswer, pet);

    }

    public Character askedForValidation() {
        System.out.println("Are you sure you want to delete this employee? (Y/N)");
        return scanner.nextLine().toUpperCase().charAt(0);
    }

    public void verifyAnswer(Character answer, Pet pet) {
        if (answer.equals('Y')) {
            deletePet(pet);
        } else if (answer.equals('N')) {
            chooseMenuOptions();
        } else {
            //do something
        }
    }

    void deletePet(Pet pet) {
        Integer id = pet.getPetId();
        pet = dao.getPet(id);
        dao.deletePet(pet);
    }
    //case 3 end


    //CASE 2 START
    public void showMenuForUpdate() {
        System.out.println("" +
                "1. Update pet status vaccinated \n" +
                "2. Update pet age \n" +
                "3. Return!");
    }

    public Pet selectPetById() {
        System.out.print("Insert pet s id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Pet pet = dao.getPet(id);
        printPet(pet);
        return pet;
    }

    public void chooseFieldToUpdate() {
        showAllPetsFromDatabase();
        Pet pet = selectPetById();
        System.out.println();
        showMenuForUpdate();
        System.out.print("Choose a valid option: ");
        Integer userOption = scanner.nextInt();
        scanner.nextLine();
        switch (userOption) {
            case 1:
                System.out.print("Insert the new vaccination status value (Y/N): ");
                Character newVaccineStatus = scanner.nextLine().toUpperCase().charAt(0);
                updateMethodFields(pet, null, newVaccineStatus, PetFields.PET_VACCINATION.getName());
                break;
            case 2:
                System.out.print("Insert the new age value: ");
                Integer newAge = scanner.nextInt();
                scanner.nextLine();
                updateMethodFields(pet, newAge, null, PetFields.PET_AGE.getName());
                break;
            case 3:
                chooseMenuOptions();
            default:
                new MenuEmployee().invalidOptionMessage();

        }
    }

    public Pet updateMethodFields(Pet pet, Integer newAge, Character newVaccineStatus, String verifyCondition) {
        Pet petWithFildUpdated = verifyConditionForUpdate(pet, newAge, newVaccineStatus, verifyCondition);
        dao.updatePet(petWithFildUpdated);
        return petWithFildUpdated;
    }

    public Pet verifyConditionForUpdate(Pet pet, Integer newAge, Character newVaccineStatus, String verifyCondition) {
        if (verifyCondition.equals(PetFields.PET_AGE.getName())) {
            getPetById(pet);
            pet.setAge(newAge);
            printPet(pet);
        } else if (verifyCondition.equals(PetFields.PET_VACCINATION.getName())) {
            getPetById(pet);
            Boolean newVaccine = booleanType(newVaccineStatus);
            pet.setVaccinated(newVaccine);
            printPet(pet);
        }
        return pet;
    }

    public Pet getPetById (Pet pet){
        Integer id = pet.getPetId();
        return dao.getPet(id);
    }

    public void printPet(Pet pet) {
        System.out.println("========================================================");
        System.out.println(pet);
        System.out.println("========================================================");
    }

    //CASE 2 END


    //CASE 1 START
    public String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    public Pet createPetByUser() {
        String petName = insertField(PetFields.PET_NAME.getName());
        System.out.print("Insert Pet's age: ");
        Integer petAge = scanner.nextInt();
        scanner.nextLine();
        String petType = insertField(PetFields.PET_TYPE.getName());
        String petBreed = insertField(PetFields.PET_BREED.getName());
        System.out.print("Insert Pet's vaccinated status: ");
        Character petVaccinatedStatus = scanner.nextLine().toUpperCase().charAt(0);
        String petOwnerName = insertField(PetFields.OWNER_NAME.getName());
        Boolean isVaccinated = booleanType(petVaccinatedStatus);
        return new Pet(petName, petAge, petType, petBreed, isVaccinated, petOwnerName);
    }

    public static boolean booleanType(Character petVaccinatedStatus) {
        if (petVaccinatedStatus == 'Y') {
            return true;
        } else {
            return false;
        }
    }

    public void addPet() {
        System.out.println("Add a new Pet: ");
        Pet pet = createPetByUser();
        dao.addPet(pet);
    }

    //CASE 1 END


    public void succesfullySaved() {
        System.out.println("\nʕ•́ᴥ•̀ʔっ");
        System.out.println("Your update was succesfully saved!");
    }

    public void goodByeMessage() {
        System.out.println("\n\uD83D\uDC4B≧◉ᴥ◉≦ \n" +
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