package spdu2022.java.project.beutysalon.entities;

import java.time.LocalTime;

public class WorkingTimePeriod {
    private LocalTime startWorking;
    private LocalTime endWorking;

    public WorkingTimePeriod() {
    }

    public LocalTime getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(LocalTime startWorking) {
        this.startWorking = startWorking;
    }

    public LocalTime getEndWorking() {
        return endWorking;
    }

    public void setEndWorking(LocalTime endWorking) {
        this.endWorking = endWorking;
    }

    @Override
    public String toString() {
        return "WorkingPeriod{" +
                "startWorking='" + startWorking + '\'' +
                ", endWorking='" + endWorking + '\'' +
                '}';
    }
}