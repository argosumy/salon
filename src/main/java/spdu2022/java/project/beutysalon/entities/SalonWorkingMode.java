package spdu2022.java.project.beutysalon.entities;

import java.util.HashSet;
import java.util.Set;

public class SalonWorkingMode {
    private long salonId;
    private final Set<WorkingDay> salonWorkingModeUniq = new HashSet<>();
    private final Set<WorkingDayOfWeek> salonWorkingDaysOfWeek = new HashSet<>();

    public SalonWorkingMode() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public Set<WorkingDay> getSalonWorkingModeUniq() {
        return salonWorkingModeUniq;
    }

    public Set<WorkingDayOfWeek> getSalonsWorkingDaysOfWeek() {
        return salonWorkingDaysOfWeek;
    }

    public void addWorkingDay(WorkingDay workingDay) {
        salonWorkingModeUniq.add(workingDay);
    }

    public void addWorkingDayOfWeek(WorkingDayOfWeek workingDayOfWeek) {
        salonWorkingDaysOfWeek.add(workingDayOfWeek);
    }

    @Override
    public String toString() {
        return "SalonWorkingMode{" +
                "salonId=" + salonId +
                ", salonWorkingModeUniq=" + salonWorkingModeUniq +
                ", workingDaysOfWeek=" + salonWorkingDaysOfWeek +
                '}';
    }
}
