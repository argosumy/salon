package spdu2022.java.project.beutysalon.entities;

import java.time.LocalDate;

public class LogService {
    private long staffId;
    private final long userId;
    private final WorkingDay workingDay;

    public LogService(long userId, LocalDate dateService) {
        this.userId = userId;
        workingDay = new WorkingDay(dateService);
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
}
