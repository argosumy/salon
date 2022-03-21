package spdu2022.java.project.beutysalon.log_book_services.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;
import spdu2022.java.project.beutysalon.entities.SlotsLog;
import spdu2022.java.project.beutysalon.log_book_services.controllers.dto.LogBookServicesDto;
import spdu2022.java.project.beutysalon.log_book_services.services.LogBookSelectService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("api/v1/log-book-services/salons")
@Validated
public class LogBookServicesController {
    private final LogBookSelectService logBookSelectService;

    public LogBookServicesController(LogBookSelectService logBookSelectService) {
        this.logBookSelectService = logBookSelectService;
    }

    @GetMapping("/{salonId}")
    @ResponseStatus(HttpStatus.OK)
    public LogBookServicesDto getLogBookServicesBySalonId(@PathVariable("salonId") @Min(1) long salonId,
                                                          @RequestParam("start-period") @DateValid String startPeriod,
                                                          @RequestParam("end-period") @DateValid String endPeriod) {
        List<SlotsLog> slotsLogs =  logBookSelectService.findLogBookServiceBySalonId(salonId, startPeriod, endPeriod);
        return new LogBookServicesDto(slotsLogs);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public LogBookServicesDto getLogBookServicesByCity(@RequestParam("start-date") @DateValid String startDate,
                                                       @RequestParam("end-date") @DateValid String endDate,
                                                       @RequestParam(value = "city") @NotBlank String city) {
        List<SlotsLog> slotsLogs = logBookSelectService.findLogBookServiceByCity(city,startDate, endDate);
        return new LogBookServicesDto(slotsLogs);
    }


}
