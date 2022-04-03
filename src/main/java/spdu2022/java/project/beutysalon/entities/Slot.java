package spdu2022.java.project.beutysalon.entities;

import java.time.LocalTime;
import java.util.Objects;

public class Slot implements Comparable<Slot>{
    private boolean freeSlot;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Slot(LocalTime startTime, int interval) {
        this.freeSlot = true;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(interval);
    }

    public boolean isFreeSlot() {
        return freeSlot;
    }

    public void setFreeSlot(boolean freeSlot) {
        this.freeSlot = freeSlot;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Slot slot) {
        return this.getStartTime().compareTo(slot.getStartTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(startTime, slot.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime);
    }

    @Override
    public String toString() {
        return "Slot{" +
                "freeSlot=" + freeSlot +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
