package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import spdu2022.java.project.beutysalon.entities.WorkingDay;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SalonWorkingDayModeDto {
    private long salonId;
    @Valid
    private List<WorkingDay> salonWorkingPeriodList = new ArrayList<>();

    public SalonWorkingDayModeDto() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public List<WorkingDay> getSalonWorkingPeriodList() {
        return salonWorkingPeriodList;
    }

    public void setSalonWorkingPeriodList(List<WorkingDay> salonWorkingPeriodList) {
        this.salonWorkingPeriodList = salonWorkingPeriodList;
    }

}