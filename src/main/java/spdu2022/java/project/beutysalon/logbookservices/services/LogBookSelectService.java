package spdu2022.java.project.beutysalon.logbookservices.services;

import spdu2022.java.project.beutysalon.entities.SlotsLog;

import java.time.LocalDate;
import java.util.List;

public interface LogBookSelectService {
    List<SlotsLog> findLogBookServiceBySalonId(long salonId, LocalDate startPeriod, LocalDate endPeriod);

    List<SlotsLog> findLogBookServiceByCity(String city, LocalDate startPeriod, LocalDate endPeriod);
}