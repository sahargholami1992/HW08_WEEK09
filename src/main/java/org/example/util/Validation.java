package org.example.util;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidPasswordWithRegex(String password) {
        Pattern pattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return password.matches(pattern.pattern());
    }
    public static boolean isValidEmailWithRegex(String password) {
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        return password.matches(pattern.pattern());
    }
    public static boolean isValidNationalCodeWithRegex(String password) {
        Pattern pattern =
                Pattern.compile("^[0-9]{10}$");
        return password.matches(pattern.pattern());
    }
    public static boolean isValidWebsiteWithRegex(String password) {
        Pattern pattern =
                Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        return password.matches(pattern.pattern());
    }
    public static boolean isValidMobileNumberWithRegex(String password) {
        Pattern pattern =
                Pattern.compile("^(00989|\\+989|09)(\\d{9})$");
        return password.matches(pattern.pattern());
    }


}
