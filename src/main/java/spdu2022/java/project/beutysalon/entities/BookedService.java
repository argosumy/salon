package spdu2022.java.project.beutysalon.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class BookedService {
    private Salon salon;
    private Staff staff;
    private final User user;
    private final WorkingDay workingDay;

    public BookedService(Salon salon, Staff staff, User user, WorkingDay workingDay) {
        this.salon = salon;
        this.staff = staff;
        this.user = user;
        this.workingDay = workingDay;
    }

    public BookedService(long userId, LocalDate dateService) {
        this.user = new User();
        user.setId(userId);
        workingDay = new WorkingDay(dateService);
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalonId(Salon salon) {
        this.salon = salon;
    }

    public long getSalonId() {
        return this.salon.getId();
    }

    public void setSalonId(long salonId) {
        this.salon.setId(salonId);
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public long getStaffId() {
        return staff.getId();
    }

    public void setStaffId(long staffId) {
        staff.setId(staffId);
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return user.getId();
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

    public String getUserEmail() {
        return user.getEmail();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, workingDay);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedService bookedService = (BookedService) o;
        return Objects.equals(this.user, bookedService.user) &&
                Objects.equals(this.workingDay, bookedService.workingDay);
    }
}
