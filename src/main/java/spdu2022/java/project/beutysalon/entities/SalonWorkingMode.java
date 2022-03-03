package spdu2022.java.project.beutysalon.entities;

import java.util.ArrayList;
import java.util.List;

public class SalonWorkingMode {
    private long salonId;
    private final List<WorkingPeriod> salonWorkingPeriods = new ArrayList<>();

    public SalonWorkingMode() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public List<WorkingPeriod> getSalonWorkingPeriods() {
        return salonWorkingPeriods;
    }

    public static class WorkingPeriod {
        private String date;
        private String startWorking;
        private String endWorking;

        public WorkingPeriod() {
        }

        public WorkingPeriod(String date, String startWorking, String endWorking) {
            this.date = date;
            this.startWorking = startWorking;
            this.endWorking = endWorking;
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

        @Override
        public String toString() {
            return "WorkingPeriod{" +
                    "date='" + date + '\'' +
                    ", startWorking='" + startWorking + '\'' +
                    ", endWorking='" + endWorking + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SalonWorkingMode{" +
                "salonId=" + salonId +
                ", salonWorkingPeriods= " + salonWorkingPeriods +
                "}";
    }
}
