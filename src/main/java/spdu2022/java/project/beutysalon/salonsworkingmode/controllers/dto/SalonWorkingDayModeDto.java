package spdu2022.java.project.beutysalon.salonsworkingmode.controllers.dto;

import spdu2022.java.project.beutysalon.entities.WorkingDay;

import javax.validation.Valid;
import java.util.Set;

public class SalonWorkingDayModeDto {
    private long salonId;
    @Valid
    private Set<WorkingDay> salonWorkingPeriodList;

    public SalonWorkingDayModeDto() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public Set<WorkingDay> getSalonWorkingPeriodList() {
        return salonWorkingPeriodList;
    }

    public void setSalonWorkingPeriodList(Set<WorkingDay> salonWorkingPeriodList) {
        this.salonWorkingPeriodList = salonWorkingPeriodList;
    }
}