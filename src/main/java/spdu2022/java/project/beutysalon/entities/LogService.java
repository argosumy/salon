package spdu2022.java.project.beutysalon.entities;

public class LogService extends WorkingDayPeriod{
    private long staffId;
    private long userId;

    public LogService() {
        super();
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

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
