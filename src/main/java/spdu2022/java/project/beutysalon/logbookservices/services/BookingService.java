package spdu2022.java.project.beutysalon.logbookservices.services;

import spdu2022.java.project.beutysalon.entities.BookedService;

public interface BookingService {
    long bookingService(BookedService bookingServicePeriod);
}