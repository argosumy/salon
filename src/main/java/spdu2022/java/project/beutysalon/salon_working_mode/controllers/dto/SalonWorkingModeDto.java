package spdu2022.java.project.beutysalon.salon_working_mode.controllers.dto;

import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

public class SalonWorkingModeDto {

    private long salonId;
    @Valid
    private List<WorkingPeriod> salonWorkingMode;

    public SalonWorkingModeDto() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public List<WorkingPeriod> getSalonWorkingMode() {
        return salonWorkingMode;
    }

    public void setSalonWorkingMode(List<WorkingPeriod> salonWorkingMode) {
        this.salonWorkingMode = salonWorkingMode;
    }

    public static class WorkingPeriod {
        @DateValid()
        private String date;
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time not valid (01:15)")
        private String startWorking;
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "time not valid (01:15)")
        private String endWorking;

        public WorkingPeriod() {
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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
}