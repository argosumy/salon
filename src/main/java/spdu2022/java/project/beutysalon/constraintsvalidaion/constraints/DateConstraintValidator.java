package spdu2022.java.project.beutysalon.constraintsvalidaion.constraints;

import spdu2022.java.project.beutysalon.constraintsvalidaion.annotations.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateConstraintValidator implements ConstraintValidator<DateValid, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        try {
            LocalDate now = LocalDate.now();
            return  localDate.isAfter(now.minusDays(1));
        } catch (Exception e) {
            return false;
        }
    }
}
