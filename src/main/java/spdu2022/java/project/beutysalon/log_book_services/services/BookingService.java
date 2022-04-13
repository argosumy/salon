package spdu2022.java.project.beutysalon.log_book_services.services;

import spdu2022.java.project.beutysalon.entities.BookedService;

public interface BookingService {
    long bookingService(BookedService bookingServicePeriod);
}
