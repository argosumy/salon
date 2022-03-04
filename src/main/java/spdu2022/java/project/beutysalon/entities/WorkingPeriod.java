package spdu2022.java.project.beutysalon.entities;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

@Component
public abstract class WorkingPeriod {
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time not valid (01:15)")
    private String startWorking;
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time not valid (01:15)")
    private String endWorking;

    public WorkingPeriod() {
    }

    public String getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(String startWorking) {
        this.startWorking = startWorking;
    }

    public String getEndWorking() {
        return endWorking;
    }

    public void setEndWorking(String endWorking) {
        this.endWorking = endWorking;
    }
}
