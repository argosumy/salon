package spdu2022.java.project.beutysalon.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class WorkingTimePeriod {
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startWorking;
    @DateTimeFormat(pattern = "HH:mm")
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