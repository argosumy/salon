package spdu2022.java.project.beutysalon.entities;

import spdu2022.java.project.beutysalon.entities.enums.DaysOfWeek;

import java.util.Objects;

public class WorkingDayOfWeekPeriod extends WorkingPeriod{
    private DaysOfWeek daysOfWeek;

    public WorkingDayOfWeekPeriod() {
        super();
    }

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDayOfWeekPeriod that = (WorkingDayOfWeekPeriod) o;
        return daysOfWeek == that.daysOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(daysOfWeek);
    }

    @Override
    public String toString() {
        return "WorkingDayOfWeekPeriod{" +
                "daysOfWeek=" + daysOfWeek + ", " + super.toString() +
                '}';
    }
}
