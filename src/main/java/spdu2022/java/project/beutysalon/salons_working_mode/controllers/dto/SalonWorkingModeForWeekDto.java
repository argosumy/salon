package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;

import java.util.Set;

public class SalonWorkingModeForWeekDto {
    private long salonId;
    private Set<WorkingDayOfWeek> weekDtoList;

    public SalonWorkingModeForWeekDto() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public Set<WorkingDayOfWeek> getWeekDtoList() {
        return weekDtoList;
    }

    public void setWeekDtoList(Set<WorkingDayOfWeek> weekDtoList) {
        this.weekDtoList = weekDtoList;
    }

}
