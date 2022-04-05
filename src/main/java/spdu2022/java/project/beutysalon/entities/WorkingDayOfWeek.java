package spdu2022.java.project.beutysalon.entities;

import java.time.DayOfWeek;
import java.util.Objects;

public class WorkingDayOfWeek implements WorkingMode{
    private final DayOfWeek dayOfWeek;
    private final WorkingTimePeriod workingTimePeriod = new WorkingTimePeriod();

    public WorkingDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public WorkingTimePeriod getWorkingTimePeriod() {
        return workingTimePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDayOfWeek that = (WorkingDayOfWeek) o;
        return dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek);
    }

    @Override
    public String toString() {
        return "WorkingDayOfWeekPeriod{" +
                "daysOfWeek=" + dayOfWeek + ", " + super.toString() +
                '}';
    }
}
