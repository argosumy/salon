package spdu2022.java.project.beutysalon.constraints_validaion.annotations;

import spdu2022.java.project.beutysalon.constraints_validaion.constraints.DateConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Constraint(validatedBy = DateConstraintValidator.class)
@Target({FIELD,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    public String message() default "Date pattern:\"yyyy-MM-dd\" Date must be in future";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
