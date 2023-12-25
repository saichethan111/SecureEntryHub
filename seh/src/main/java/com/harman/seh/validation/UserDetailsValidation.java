package com.harman.seh.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetailsValidation {

    public boolean passwordValidation(String password){
        return Pattern.compile("^(?=.*[A-Z])(?=.*\\d).+$")
                .matcher(password)
                .matches();
    }
}
