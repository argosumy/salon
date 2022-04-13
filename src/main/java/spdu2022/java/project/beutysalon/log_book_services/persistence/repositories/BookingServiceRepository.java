package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.BookedService;

public interface BookingServiceRepository {
    long bookingService(BookedService bookingServicePeriod);
}
