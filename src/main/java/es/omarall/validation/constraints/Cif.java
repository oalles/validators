package es.omarall.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CifValidator.class)
@Documented
public @interface Cif {

//	String message() default "{cif}";
	String message() default "CIF invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
