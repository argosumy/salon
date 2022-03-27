package spdu2022.java.project.beutysalon.entities;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public abstract class WorkingPeriod {
    private LocalTime startWorking;
    private LocalTime endWorking;

    public WorkingPeriod() {
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
