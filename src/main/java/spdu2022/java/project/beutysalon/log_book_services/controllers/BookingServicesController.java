package spdu2022.java.project.beutysalon.log_book_services.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.LogService;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.BookingServicesDto;
import spdu2022.java.project.beutysalon.log_book_services.services.BookingService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/staff/{staffId}/booking-services")
public class BookingServicesController {
    private final BookingService bookingService;

    public BookingServicesController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public long bookingService(@RequestBody @Valid BookingServicesDto dto, @PathVariable("staffId") long staffId) {
        LogServiceMapper mapper = new LogServiceMapper();
        LogService bookingServicePeriod = (LogService) mapper.convertBookingServiceDtoToLogService(dto);
        bookingServicePeriod.setStaffId(staffId);
        return bookingService.bookingService(bookingServicePeriod);
    }



}
