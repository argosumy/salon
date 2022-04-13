package spdu2022.java.project.beutysalon.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookedService {
    private long salonId;
    private long staffId;
    private final long userId;
    private final WorkingDay workingDay;

    public BookedService(long userId, LocalDate dateService) {
        this.userId = userId;
        workingDay = new WorkingDay(dateService);
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public long getUserId() {
        return userId;
    }

    public WorkingDay getWorkingDayPeriod() {
        return workingDay;
    }

    public void addWorkingTimePeriodForDay(LocalTime start, LocalTime end) {
        workingDay.addWorkingTimePeriod(start, end);
    }

    public LocalDate getWorkingDay() {
        return workingDay.getWorkingDay();
    }

    public WorkingTimePeriod getWorkingTimePeriod() {
        return getWorkingDayPeriod().getWorkingTimePeriod();
    }




}
