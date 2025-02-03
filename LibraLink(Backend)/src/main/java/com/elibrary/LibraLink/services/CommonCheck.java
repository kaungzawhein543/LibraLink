package com.elibrary.LibraLink.services;

public class CommonCheck {

    // CONSTANT VALUES
    private final int passwordLength;
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";

    // CONSTRUCTOR
    public CommonCheck(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    // CHECK PASSWORD LENGTH
    private boolean checkPasswordLength(String password) {
        if (password == null) {
            return false; // Null passwords are invalid
        }
        return password.length() >= passwordLength;
    }

    // CHECK PASSWORD FORMAT
    public boolean checkPasswordFormat(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    // VALIDATE THE PASSWORD
    public boolean isPasswordValid(String password) {
        return checkPasswordLength(password) && checkPasswordFormat(password);
    }
}
