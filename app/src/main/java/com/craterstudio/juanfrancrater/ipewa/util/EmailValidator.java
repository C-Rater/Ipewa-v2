package com.craterstudio.juanfrancrater.ipewa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Comprueba el correo, es una version basica
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */

public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        boolean isValid = false;
        CharSequence inputStr = hex;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;

    }
}
