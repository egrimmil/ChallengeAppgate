package com.elkin.challengeappgate.utils;

public class DataUtil {
    static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    static String passPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public static boolean isValidEmail(String email) {
        return email.matches(emailPattern) && email.length() > 0;
    }

    public static boolean isValidPass(String pass){
        return pass.matches(passPattern);
    }
}
