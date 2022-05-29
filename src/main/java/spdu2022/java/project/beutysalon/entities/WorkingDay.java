package spdu2022.java.project.beutysalon.entities;

import org.springframework.format.annotation.DateTimeFormat;
import spdu2022.java.project.beutysalon.constraintsvalidaion.annotations.DateValid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingDay {
    @DateValid
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate workingDay;
    private final WorkingTimePeriod workingTimePeriod = new WorkingTimePeriod();

    public WorkingDay() {
    }

    public WorkingDay(LocalDate workingDay) {
        this.workingDay = workingDay;
    }

    public LocalDate getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(LocalDate workingDay) {
        this.workingDay = workingDay;
    }

    public WorkingTimePeriod getWorkingTimePeriod() {
        return workingTimePeriod;
    }

    public void addWorkingTimePeriod(LocalTime startTime, LocalTime endTime) {
        workingTimePeriod.setStartWorking(startTime);
        workingTimePeriod.setEndWorking(endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
