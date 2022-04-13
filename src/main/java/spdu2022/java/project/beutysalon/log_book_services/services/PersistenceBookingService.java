package spdu2022.java.project.beutysalon.log_book_services.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.log_book_services.persistence.repositories.BookingServiceRepository;

@Service
public class PersistenceBookingService implements BookingService {
    private final BookingServiceRepository repository;

    public PersistenceBookingService(BookingServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public long bookingService(BookedService bookingServicePeriod) {
        return repository.bookingService(bookingServicePeriod);
    }
}
