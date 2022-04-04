package spdu2022.java.project.beutysalon.entities;

import java.util.ArrayList;
import java.util.List;

public class SalonWorkingMode {
    private long salonId;
    private final List<WorkingMode> salonWorkingMode = new ArrayList<>();

    public SalonWorkingMode() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public List<WorkingMode> getSalonWorkingMode() {
        return salonWorkingMode;
    }

    @Override
    public String toString() {
        return "SalonWorkingMode{" +
                "salonId=" + salonId +
                ", salonWorkingMode= " + salonWorkingMode +
                "}";
    }
}
