package com.sda.practical.services;

import com.sda.practical.databases.model.Pet;
import com.sda.practical.databases.model.Speciality;
import com.sda.practical.databases.model.Type;
import com.sda.practical.databases.model.model_fields.PetFields;
import com.sda.practical.databases.repository.PetRepository;
import com.sda.practical.databases.repository.TypeRepository;
import com.sda.practical.views.ConsolePrints;
import com.sda.practical.views.MenuService;
import com.sda.practical.views.MenuType;

import java.util.List;
import java.util.Scanner;

public class MenuPet {

    Scanner scanner = new Scanner(System.in);
    PetRepository petRepository = new PetRepository();
    MenuService menu = new MenuService();
    ConsolePrints print = new ConsolePrints();
    TypeRepository typeRepository = new TypeRepository();

    //CASE 1 START
    public String insertField(String fieldName) {
        System.out.print("Insert " + fieldName + ": ");
        return scanner.nextLine();
    }

    private Pet createPet() {
        Pet pet = new Pet();
        pet.setType(selectSpeciality());
        System.out.print("Insert Pet's age: ");
        pet.setAge(scanner.nextInt());
        scanner.nextLine();
        pet.setBreed(insertField(PetFields.PET_BREED));
        return pet;
    }

    private void showAllTypesFromDatabase(){
        System.out.println("《《《《《     List of Pets Types    》》》》》");
        typeRepository.findAll().forEach(t -> System.out.println("------------------------------------\n" + t));
        System.out.println("------------------------------------");
    }

    private Type selectSpeciality(){
        showAllTypesFromDatabase();
        System.out.println("Select the id corresponding to pet types for setting to the new pet!");
        System.out.print("Insert id here: ");
        int userOption = scanner.nextInt();
        scanner.nextLine();
        return typeRepository.findById(userOption);
    }

//    public static boolean booleanType(Character petVaccinatedStatus) {
//        if (petVaccinatedStatus == 'Y') {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void savePet() {
        System.out.println("Add a new Pet: ");
        Pet pet = createPet();
        petRepository.save(pet);
    }

    //CASE 1 END

    //CASE 2 START

    public Pet selectPetById() {

        System.out.print("Insert pet s id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Pet pet = petRepository.findById(id);
        printPet(pet);
        return pet;
    }
    //
//    public void chooseFieldToUpdate() {
//        showAllPetsFromDatabase();
//        Pet pet = selectPetById();
//        System.out.println();
//        menu.printMenu(MenuType.PET_UPDATE_MENU);
//        System.out.print("Choose a valid option: ");
//        int userOption = scanner.nextInt();
//        scanner.nextLine();
//        switch (userOption) {
//            case 1:
//                System.out.print("Insert the new vaccination status value (Y/N): ");
//                Character newVaccineStatus = scanner.nextLine().toUpperCase().charAt(0);
//                updateMethodFields(pet, null, newVaccineStatus, PetFields.PET_VACCINATION);
//                break;
//            case 2:
//                System.out.print("Insert the new age value: ");
//                Integer newAge = scanner.nextInt();
//                scanner.nextLine();
//                updateMethodFields(pet, newAge, null, PetFields.PET_AGE);
//                break;
//            case 3:
//                chooseMenuOptions();
//            default:
//                print.invalidOptionMessage();
//
//        }
//    }
//
//    public void updateMethodFields(Pet pet, Integer newAge, Character newVaccineStatus, String verifyCondition) {
//        Pet petWithFildUpdated = verifyConditionForUpdate(pet, newAge, newVaccineStatus, verifyCondition);
//        petRepository.update(petWithFildUpdated);
//    }
//
//    public Pet verifyConditionForUpdate(Pet pet, Integer newAge, Character newVaccineStatus, String verifyCondition) {
//        if (verifyCondition.equals(PetFields.PET_AGE)) {
//            pet.setAge(newAge);
//            printPet(pet);
//        } else if (verifyCondition.equals(PetFields.PET_VACCINATION)) {
//            Boolean newVaccine = booleanType(newVaccineStatus);
//            pet.setIsVaccinated(newVaccine);
//            printPet(pet);
//        }
//        return pet;
//    }
//
    public void printPet(Pet pet) {
        System.out.println("========================================================");
        System.out.println(pet);
        System.out.println("========================================================");
    }

    //CASE 2 END

    //Case 3 start
    public void choosePetToDelete() {
        showAllPetsFromDatabase();
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
            //chooseMenuOptions();
        } else {
            //do something
        }
    }

    void deletePet(Pet pet) {
        Integer id = pet.getId();
        pet = petRepository.findById(id);
        petRepository.delete(pet);
    }
    //case 3 end

    //Case 4 start
    public void showAllPetsFromDatabase() {
        System.out.println("《《《《《     List of Pets    》》》》》");
        petRepository.findAll().forEach(p -> System.out.println("----------------------------------------\n" + p));
        System.out.println("-----------------------------------------------------------------------");
    }
    //Case 4 end


//    public void chooseMenuOptions() {
//        menu.printMenu(MenuType.PET_MENU);
//        System.out.print("Please choose a valid option: ");
//        Integer userOption;
//        do {
//            userOption = scanner.nextInt();
//            scanner.nextLine();
//            switch (userOption) {
//                case 1:
//                    addPet();
//                    print.successfullySaved();
//                    chooseAnotherMenuOption();
//                    break;
//                case 2:
//                    chooseFieldToUpdate();
//                    print.successfullySaved();
//                    chooseAnotherMenuOption();
//                    break;
//                case 3:
//                    showAllPetsFromDatabase();
//                    choosePetToDelete();
//                    print.successfullySaved();
//                    chooseAnotherMenuOption();
//                    break;
//                case 4:
//                    showAllPetsFromDatabase();
//                    chooseAnotherMenuOption();
//                    break;
//                case 5:
//                    new Menu().showMenu();
//                    break;
//                default:
//                    print.invalidOptionMessage();
//            }
//        } while (userOption < 1 || userOption > 5);
//    }












//    public void chooseAnotherMenuOption() {
//        System.out.print("\nDo you want to choose another option menu? (Y/N) \n" +
//                "Insert option here: ");
//        char yesOrNo;
//        do {
//            yesOrNo = scanner.nextLine().toUpperCase().charAt(0);
//            if (yesOrNo == 'Y') {
//                chooseMenuOptions();
//            } else if (yesOrNo == 'N') {
//                print.goodByeMessage();
//                System.exit(0);
//            } else {
//                print.invalidOptionMessage();
//            }
//        } while (yesOrNo != 'Y' && yesOrNo != 'Y');
//    }
}
