package spdu2022.java.project.beutysalon.logbookservices.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.BookedService;

public interface BookingServiceRepository {
    long bookingService(BookedService bookingServicePeriod);
}