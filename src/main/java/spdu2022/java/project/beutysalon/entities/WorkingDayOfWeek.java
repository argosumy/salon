package spdu2022.java.project.beutysalon.entities;

import java.util.Objects;

public class WorkingDayOfWeek extends WorkingPeriod{
    private DaysOfWeek daysOfWeek;

    public WorkingDayOfWeek() {
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
        WorkingDayOfWeek that = (WorkingDayOfWeek) o;
        return daysOfWeek == that.daysOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(daysOfWeek);
    }
}
