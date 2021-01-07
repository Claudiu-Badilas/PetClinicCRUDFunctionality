package com.sda.practical.views;

public enum EmployeeFields {
    FIRSTNAME("First Name"),
    LASTNAME("Last Name"),
    ADDRESS("Address"),
    SPECIALITY("Speciality");

    private String name;

    EmployeeFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
