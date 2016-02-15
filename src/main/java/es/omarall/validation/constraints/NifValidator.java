package es.omarall.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aeat.valida.Validador;

public class NifValidator implements ConstraintValidator<Nif, String> {

    public void initialize(Nif constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Validador val = new Validador();
        int nif1 = val.checkNif(value);
        val.vNif(value);
        if (nif1 == Validador.NIF_OK) {
            return true;
        }
        return false;
    }
}
