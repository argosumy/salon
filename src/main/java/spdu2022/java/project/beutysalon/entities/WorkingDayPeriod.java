package spdu2022.java.project.beutysalon.entities;

import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import java.util.Objects;

public class WorkingDayPeriod extends WorkingPeriod {
    @DateValid
    private String workingDay;

    public WorkingDayPeriod() {
        super();
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDayPeriod dayPeriod = (WorkingDayPeriod) o;
        return Objects.equals(workingDay, dayPeriod.workingDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingDay);
    }
}
