package spdu2022.java.project.beutysalon.log_book_services.controllers;

import spdu2022.java.project.beutysalon.entities.LogService;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.BookingServicesDto;

public class LogServiceMapper {
    LogService convertBookingServiceDtoToLogService(BookingServicesDto dto) {
        LogService logService = new LogService(dto.getUserId(), dto.getBookingDate());
        logService.getWorkingDayPeriod().getWorkingTimePeriod().setStartWorking(dto.getStart());
        logService.getWorkingDayPeriod().getWorkingTimePeriod().setEndWorking(dto.getEnd());
        return logService;
    }
}
