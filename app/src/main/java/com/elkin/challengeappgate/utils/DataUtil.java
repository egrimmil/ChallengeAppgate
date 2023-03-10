package com.elkin.challengeappgate.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DataUtil {
    static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    static String passPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*.-]).{8,}$";

    public static boolean isValidEmail(String email) {
        return email.matches(emailPattern) && email.length() > 0;
    }

    public static boolean isValidPass(String pass){
        return pass.matches(passPattern);
    }

    public static String dateFormatToFormat(@NonNull String date, @NonNull String input, @NonNull String output){
        SimpleDateFormat simpleInput = new SimpleDateFormat(input, Locale.getDefault());
        SimpleDateFormat simpleOutput = new SimpleDateFormat(output, Locale.getDefault());
        try{
            return simpleOutput.format(Objects.requireNonNull(simpleInput.parse(date)));
        } catch (Exception ex){
            return "";
        }
    }
}
