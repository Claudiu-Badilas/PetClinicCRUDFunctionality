package com.sda.practical.views;

public enum PetFields {
    PET_NAME("pet name"),
    PET_AGE("pet age"),
    PET_TYPE("pet type"),
    PET_BREED("pet breed"),
    PET_VACCINATION("vaccination status"),
    OWNER_NAME("owner name");

    private String name;

    PetFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
