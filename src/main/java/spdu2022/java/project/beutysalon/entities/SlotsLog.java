package spdu2022.java.project.beutysalon.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SlotsLog {
    private long salonId;
    private LocalDate date;
    //key staffId
    private final Map<Long, Set<Slot>> slotListForAllStaff = new HashMap<>();

    public SlotsLog() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Long, Set<Slot>> getSlotMap() {
        return slotListForAllStaff;
    }

    @Override
    public String toString() {
        return "SlotLog{" +
                "salonId=" + salonId +
                ", date=" + date +
                ", slotListForAllStaff=" + slotListForAllStaff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotsLog slotsLog = (SlotsLog) o;
        return salonId == slotsLog.salonId &&
                Objects.equals(date, slotsLog.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salonId, date);
    }
}
