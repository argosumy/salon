package spdu2022.java.project.beutysalon.log_book_services.services;

import spdu2022.java.project.beutysalon.entities.SlotsLog;

import java.util.List;

public interface LogBookSelectService {
    List<SlotsLog> findLogBookServiceBySalonId(long salonId, String startPeriod, String endPeriod);
    List<SlotsLog> findLogBookServiceByCity(String city, String startPeriod, String endPeriod);
}
