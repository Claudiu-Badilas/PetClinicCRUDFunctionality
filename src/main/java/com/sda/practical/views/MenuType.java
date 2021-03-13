package com.sda.practical.views;

public enum MenuType {
    LOGIN_MENU(1),
    MAIN_MENU(2),
    EMPLOYEE_MENU(3),
    EMPLOYEE_UPDATE_MENU(4),
    PET_MENU(5),
    PET_UPDATE_MENU(6),
    EXAMINATION_MENU(7),
    USER_MENU(8);

    private int menuId;

    MenuType(int id) {
        this.menuId = id;
    }

    public int getMenuId() {
        return menuId;
    }
}
