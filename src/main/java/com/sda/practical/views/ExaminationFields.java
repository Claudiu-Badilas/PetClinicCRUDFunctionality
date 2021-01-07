package com.sda.practical.views;

public enum ExaminationFields {
    CHECK_IN("checkIn (DD/MM/YYYY)"),
    CHECK_OUT("checkOut (DD/MM/YYYY)"),
    TREATMENT("treatment");

    private String name;

    ExaminationFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
