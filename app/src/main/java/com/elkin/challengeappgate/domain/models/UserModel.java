package com.elkin.challengeappgate.domain.models;

public class UserModel {
    private final String userEmail;
    private final String userPass;

    public UserModel(String userEmail, String userPass) {
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPass() {
        return userPass;
    }
}
