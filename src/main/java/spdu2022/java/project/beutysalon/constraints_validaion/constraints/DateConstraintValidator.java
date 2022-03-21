package spdu2022.java.project.beutysalon.constraints_validaion.constraints;

import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConstraintValidator implements ConstraintValidator<DateValid,String> {
    @Override
    public boolean isValid(String dateText, ConstraintValidatorContext context) {
        try {
            LocalDate dateMode = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate now = LocalDate.now();
            return  dateMode.isAfter(now.minusDays(1));
        } catch (Exception e) {
            return false;
        }
    }
}
