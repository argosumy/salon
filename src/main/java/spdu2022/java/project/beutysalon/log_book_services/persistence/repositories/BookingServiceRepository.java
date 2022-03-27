package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.WorkingPeriod;

public interface BookingServiceRepository {
    long bookingService(WorkingPeriod bookingServicePeriod);
}
