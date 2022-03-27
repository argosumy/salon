package spdu2022.java.project.beutysalon.log_book_services.controllers;

import spdu2022.java.project.beutysalon.entities.LogService;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.BookingServicesDto;

public class LogServiceMapper {
    WorkingPeriod convertBookingServiceDtoToLogService(BookingServicesDto dto) {
        LogService logService = new LogService();
        logService.setUserId(dto.getUserId());
        logService.setWorkingDay(dto.getBookingDate());
        logService.setStartWorking(dto.getStart());
        logService.setEndWorking(dto.getEnd());
        return logService;
    }
}
