package com.elkin.challengeappgate.domain;

public class UserModel {
    private final String userName;
    private final String userPass;

    public UserModel(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }
}
