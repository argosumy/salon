package spdu2022.java.project.beutysalon.logbookservices.controllers.dto;

import spdu2022.java.project.beutysalon.entities.SlotsLog;

import java.util.List;

public class LogBookServicesDto {
    private List<SlotsLog> slotsLogs;

    public LogBookServicesDto() {
    }

    public LogBookServicesDto(List<SlotsLog> slotsLogs) {
        this.slotsLogs = slotsLogs;
    }

    public List<SlotsLog> getSlotLogs() {
        return slotsLogs;
    }

    public void setSlotLogs(List<SlotsLog> slotsLogs) {
        this.slotsLogs = slotsLogs;
    }
}
