package spdu2022.java.project.beutysalon.constraints_validaion.annotations;

import spdu2022.java.project.beutysalon.constraints_validaion.constraints.TypeNotificationConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = TypeNotificationConstraintValidator.class)
@Target({FIELD,PARAMETER,METHOD,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeNotificationValid {
    public String message() default "Type of notification not valid";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
