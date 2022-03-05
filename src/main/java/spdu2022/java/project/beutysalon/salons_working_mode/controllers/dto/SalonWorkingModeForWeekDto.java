package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;

import java.util.Set;

public class SalonWorkingModeForWeekDto {
    private long salonId;
    private Set<WorkingDayOfWeekPeriod> weekDtoList;

    public SalonWorkingModeForWeekDto() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public Set<WorkingDayOfWeekPeriod> getWeekDtoList() {
        return weekDtoList;
    }

    public void setWeekDtoList(Set<WorkingDayOfWeekPeriod> weekDtoList) {
        this.weekDtoList = weekDtoList;
    }

}
