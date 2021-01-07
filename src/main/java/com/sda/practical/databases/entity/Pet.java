package com.sda.practical.databases.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pets")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    private Integer petId;

    @Column(name = "PET_NAME")
    private String petName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "PET_TYPE")
    private String petType;

    @Column(name = "BREED")
    private String breed;

    @Column(name = "IS_VACCINATED")
    private Boolean isVaccinated;

    @Column(name = "OWNER_NAME")
    private String ownerName;


    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Examination> examinationsForPet;


    public Pet() {
    }

    public Pet(String petName, Integer age, String petType, String breed, Boolean isVaccinated, String ownerName) {
        this.petName = petName;
        this.age = age;
        this.petType = petType;
        this.breed = breed;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<Examination> getExaminationsForPet() {
        return examinationsForPet;
    }

    public void setExaminationsForPet(Set<Examination> examinationsForPet) {
        this.examinationsForPet = examinationsForPet;
    }

    @Override
    public String toString() {
        return petId + " | " + petName + " | " + age + " | " + petType + " | " + breed + " | " + isVaccinated + " | " + ownerName;
    }
}
