package spdu2022.java.project.beutysalon.constraints_validaion.constraints;

import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConstraintValidator implements ConstraintValidator<DateValid,String> {
    @Override
    public boolean isValid(String dateText, ConstraintValidatorContext context) {
        try {
            Date dateMode = new SimpleDateFormat("yyyy-MM-dd").parse(dateText);
            Date now = new Date();
            return  dateMode.after(now);
        } catch (Exception e) {
            return false;
        }
    }
}
