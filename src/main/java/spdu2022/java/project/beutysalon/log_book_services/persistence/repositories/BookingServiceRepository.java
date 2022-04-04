package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.LogService;
import spdu2022.java.project.beutysalon.entities.WorkingTimePeriod;

public interface BookingServiceRepository {
    long bookingService(LogService bookingServicePeriod);
}
