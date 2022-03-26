package spdu2022.java.project.beutysalon.entities;

import java.time.DayOfWeek;
import java.util.Objects;

public class WorkingDayOfWeekPeriod extends WorkingPeriod{
    private DayOfWeek dayOfWeek;

    public WorkingDayOfWeekPeriod() {
        super();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDayOfWeekPeriod that = (WorkingDayOfWeekPeriod) o;
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
