package spdu2022.java.project.beutysalon.log_book_services.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;
import spdu2022.java.project.beutysalon.entities.SlotsLog;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.LogBookServicesDto;
import spdu2022.java.project.beutysalon.log_book_services.services.LogBookSelectService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/salons")
@Validated
public class LogBookServicesController {
    private final LogBookSelectService logBookSelectService;

    public LogBookServicesController(LogBookSelectService logBookSelectService) {
        this.logBookSelectService = logBookSelectService;
    }

    @GetMapping("/{salonId}/log-book-services")
    @ResponseStatus(HttpStatus.OK)
    public LogBookServicesDto getLogBookServicesBySalonId(@PathVariable @Min(1) long salonId,
                                                          @RequestParam("start-period") @DateValid @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startPeriod,
                                                          @RequestParam("end-period") @DateValid @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endPeriod) {
        List<SlotsLog> slotsLogs =  logBookSelectService.findLogBookServiceBySalonId(salonId, startPeriod, endPeriod);
        return new LogBookServicesDto(slotsLogs);
    }

    @GetMapping("/log-book-services")
    @ResponseStatus(HttpStatus.OK)
    public LogBookServicesDto getLogBookServicesByCity(@RequestParam("start-period") @DateValid @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startPeriod,
                                                       @RequestParam("end-period") @DateValid @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endPeriod,
                                                       @RequestParam @NotBlank String city) {
        List<SlotsLog> slotsLogs = logBookSelectService.findLogBookServiceByCity(city, startPeriod, endPeriod);
        return new LogBookServicesDto(slotsLogs);
    }


}
