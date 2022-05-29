package spdu2022.java.project.beutysalon.logbookservices.controllers;

import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.logbookservices.controllers.dto.BookingServicesDto;

public class LogServiceMapper {
    BookedService convertToLogService(BookingServicesDto dto) {
        BookedService bookedService = new BookedService(dto.getUserId(), dto.getBookingDate());
        bookedService.addWorkingTimePeriodForDay(dto.getStart(), dto.getEnd());
        return bookedService;
    }
}
