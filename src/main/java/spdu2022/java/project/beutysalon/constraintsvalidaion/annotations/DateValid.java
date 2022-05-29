package spdu2022.java.project.beutysalon.constraintsvalidaion.annotations;

import spdu2022.java.project.beutysalon.constraintsvalidaion.constraints.DateConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = DateConstraintValidator.class)
@Target({FIELD, PARAMETER, METHOD, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    String message() default "Date pattern:\"yyyy-MM-dd\" Date must be in future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}