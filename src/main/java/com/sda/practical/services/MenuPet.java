package com.sda.practical.services;

import com.sda.practical.databases.model.Pet;
import com.sda.practical.databases.model.Type;
import com.sda.practical.databases.model.model_fields.PetFields;
import com.sda.practical.databases.repository.PetRepository;
import com.sda.practical.databases.repository.TypeRepository;
import com.sda.practical.views.ConsolePrints;
import com.sda.practical.views.MenuService;

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
}
