package spdu2022.java.project.beutysalon.entities;

import org.springframework.format.annotation.DateTimeFormat;
import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import java.time.LocalDate;
import java.util.Objects;

public class WorkingDay implements WorkingMode {
    @DateValid
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate workingDay;
    private final WorkingTimePeriod workingTimePeriod = new WorkingTimePeriod();

    public WorkingDay(LocalDate workingDay) {
        this.workingDay = workingDay;
    }

    @Override
    public WorkingMode getWorkingMode() {
        return this;
    }

    public LocalDate getWorkingDay() {
        return workingDay;
    }

    public WorkingTimePeriod getWorkingTimePeriod() {
        return workingTimePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDay dayPeriod = (WorkingDay) o;
        return Objects.equals(workingDay, dayPeriod.workingDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingDay);
    }

    @Override
    public String toString() {
        return "WorkingDayPeriod{" +
                "workingDay='" + workingDay + '\'' + ", " + super.toString() +
                '}';
    }
}
