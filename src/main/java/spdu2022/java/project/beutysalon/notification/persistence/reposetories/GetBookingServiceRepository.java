package spdu2022.java.project.beutysalon.notification.persistence.reposetories;

import spdu2022.java.project.beutysalon.entities.BookedService;

import java.util.List;

public interface GetBookingServiceRepository {
    List<BookedService> getBookingBySalonId(long salonId);
}
