package spdu2022.java.project.beutysalon.logbookservices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.logbookservices.controllers.dto.BookingServicesDto;
import spdu2022.java.project.beutysalon.logbookservices.services.BookingService;

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
        BookedService bookingServicePeriod = mapper.convertToLogService(dto);
        bookingServicePeriod.setStaffId(staffId);
        return bookingService.bookingService(bookingServicePeriod);
    }
}