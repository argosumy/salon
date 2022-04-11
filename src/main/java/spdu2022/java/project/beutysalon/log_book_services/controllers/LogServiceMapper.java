package spdu2022.java.project.beutysalon.log_book_services.controllers;

import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.BookingServicesDto;

public class LogServiceMapper {
    BookedService convertToLogService(BookingServicesDto dto) {
        BookedService bookedService = new BookedService(dto.getUserId(), dto.getBookingDate());
        bookedService.addWorkingTimePeriodForDay(dto.getStart(), dto.getEnd());
        return bookedService;
    }
}
