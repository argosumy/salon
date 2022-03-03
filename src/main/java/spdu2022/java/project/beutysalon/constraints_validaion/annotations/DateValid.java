package spdu2022.java.project.beutysalon.constraints_validaion.annotations;

import spdu2022.java.project.beutysalon.constraints_validaion.constraints.DateConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    public String message() default "Date pattern:\"dd:MM:yyyy\" Date must be in future";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
