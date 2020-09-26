package com.greentower.api.rules.person.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidatorUtil {

    private static final int[] weightCpf = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcDigit(String str, int[] peso) {
        int sum = 0;
        for (int index=str.length()-1, digit; index >= 0; index-- ) {
            digit = Integer.parseInt(str.substring(index, index + 1));
            sum += digit * peso[peso.length - str.length() + index];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isCpfValid(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) return false;
        Integer digit1 = calcDigit(cpf.substring(0, 9), weightCpf);
        Integer digit2 = calcDigit(cpf.substring(0, 9) + digit1, weightCpf);
        return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
    }

    public static boolean isCnpjValido(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) return false;
        Integer digit1 = calcDigit(cnpj.substring(0, 12), weightCNPJ);
        Integer digit2 = calcDigit(cnpj.substring(0, 12) + digit1, weightCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
    }

    public static boolean isValidEmailAddress(String email) {

        boolean isEmailIdValid = false;

        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
