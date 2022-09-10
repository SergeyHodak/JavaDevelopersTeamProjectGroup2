package com.goit.javaonline5.user.service.email;

public class EmailFormatCheck {
    public static boolean isTheEmailCorrect(String email) {
        String regexp = "[A-Za-z\\d!#$%&'*+/=?^_`.{|}~-]+@[a-z\\d]+.[a-z\\d]+";
        return email.matches(regexp);
    }
}
