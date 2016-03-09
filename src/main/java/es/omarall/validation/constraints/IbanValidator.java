package es.omarall.validation.constraints;

import java.math.BigInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<Iban, String> {

    public void initialize(Iban constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        return validateIban(value);
    }

    private byte ibanCharToByte(char c) {
        return (byte) ((byte) c - 55);
    }

    private boolean validateIban(String iban) {

        // 1. The first four characters are moved to the end
        StringBuffer ibanNewOrder = new StringBuffer(iban.substring(4))
                .append(iban.substring(0, 4));

        // 2.Replace each LETTER in the String with 2 digits ...
        StringBuffer numberSb = new StringBuffer();
        for (int index = 0; index < iban.length(); index++) {
            char c = ibanNewOrder.charAt(index);
            boolean isDigit = ((c >= '0') && (c <= '9'));
            if (isDigit) {
                numberSb.append(c);
            } else {
                byte b = ibanCharToByte(c);
                numberSb.append(Byte.toString(b));
            }
        }

        // 3. it is divided by 97 ...
        BigInteger bi = new BigInteger(numberSb.toString());
        BigInteger resto = bi.mod(new BigInteger("" + 97));

        // 4. If the modulo (remainder after the integer division) is 1, then
        // the initial account number is a
        // correct ΙΒΑΝ format; else this is not an IBAN account number
        if (resto.equals(new BigInteger("" + 1))) {
            return true;
        }
        return false;
    }

}
