package es.omarall.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.aeat.valida.Validador;

public class CifValidator implements ConstraintValidator<Cif, String> {

	@Override
	public void initialize(Cif constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		Validador val = new Validador();
		int nif1 = val.checkNif(value);
		val.vNif(value);
		if (nif1 == Validador.CIF_OK) {
			return true;
		}
		return false;
	}

}
