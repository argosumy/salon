package spdu2022.java.project.beutysalon.entities;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingDayOfWeek {
    private DayOfWeek dayOfWeek;
    private WorkingTimePeriod workingTimePeriod;

    public WorkingDayOfWeek() {
    }

    public WorkingDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        workingTimePeriod = new WorkingTimePeriod();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
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
