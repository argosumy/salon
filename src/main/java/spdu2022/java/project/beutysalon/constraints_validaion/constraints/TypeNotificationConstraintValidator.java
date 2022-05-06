package spdu2022.java.project.beutysalon.constraints_validaion.constraints;

import spdu2022.java.project.beutysalon.constraints_validaion.annotations.TypeNotificationValid;
import spdu2022.java.project.beutysalon.entities.NotificationTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TypeNotificationConstraintValidator implements ConstraintValidator<TypeNotificationValid, String> {
//    public void initialize(TypeNotificationValid constraint) {
//    }
    @Override
    public boolean isValid(String typeNotification, ConstraintValidatorContext context) {
        return Arrays.stream(NotificationTypes.values()).anyMatch(x -> x.name().toUpperCase().equals(typeNotification.toUpperCase()));
    }
}
