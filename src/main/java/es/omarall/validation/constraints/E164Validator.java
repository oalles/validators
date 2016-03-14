package es.omarall.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Checks that a given character sequence (e.g. string) is a valid E164
 * formatted phonenumber.
 * https://www.cmtelecom.com/newsroom/how-to-format-international-telephone-
 * numbers https://github.com/googlei18n/libphonenumber
 */
public class E164Validator implements ConstraintValidator<E164, String> {

    private final PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();

    public void initialize(E164 constraintAnnotation) {
    }

    public boolean isValid(String value,
            final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        try {

            final PhoneNumber parsingResult = pnu.parse(value, null);
            if (!pnu.format(parsingResult, PhoneNumberFormat.E164)
                    .equals(value)) {
                return false;
            }
            return true;
        } catch (final NumberParseException t) {
            // Errors when parsing phonenumber
            return false;
        }

        // CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN
        // log.debug("Phone Number: {}", value);
        // log.debug("Country code: {}", numberProto.getCountryCode());
        // log.debug("National Number: {}", numberProto.getNationalNumber());
        // log.debug("E164 format: {}", pnu.format(numberProto,
        // PhoneNumberFormat.E164));

    }

}
