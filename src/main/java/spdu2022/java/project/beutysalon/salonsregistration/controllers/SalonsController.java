package spdu2022.java.project.beutysalon.salonsregistration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.salonsregistration.dto.SalonsDTO;
import spdu2022.java.project.beutysalon.salonsregistration.services.SalonsGetService;
import spdu2022.java.project.beutysalon.salonsregistration.services.SalonsModificationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api/v1/salons")
@Validated
public class SalonsController {
    private final SalonsModificationService salonsModificationService;
    private final SalonsGetService salonsGetService;
    private final SalonMapper salonMapper;

    public SalonsController(SalonsModificationService salonsModificationService,
                            SalonsGetService salonsGetService,
                            SalonMapper salonMapper) {
        this.salonsModificationService = salonsModificationService;
        this.salonsGetService = salonsGetService;
        this.salonMapper = salonMapper;
    }

    @GetMapping("/{salonId}")
    public SalonsDTO getSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) {
        Salon salon = salonsGetService.findById(id);
        return salonMapper.convertSalonToSalonsDto(salon);
    }

    @GetMapping
    public List<SalonsDTO> getAllSalonsFromCity(@RequestParam String city) {
        List<Salon> salons = salonsGetService.getAllSalonsFromCity(city);
        return salonMapper.convertSalonsToListSalonsDto(salons);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalonsDTO createNewSalon(@Valid @RequestBody SalonsDTO newSalon) {
        Salon salon = salonMapper.convertSalonsDtoToSalonForSalonCreate(newSalon);
        salon = salonsModificationService.createNewSalons(salon);
        return salonMapper.convertSalonToSalonsDto(salon);
    }

    @DeleteMapping("/{salonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id)  {
        if (!salonsModificationService.deleteSalonsById(id)) {
            throw new NotFoundException("Salon not found by ID " + id);
        }
    }

    @PutMapping("/{salonId}")
    @ResponseStatus(HttpStatus.OK)
    public SalonsDTO updateSalon(@Valid @RequestBody SalonsDTO salonUpdate,
                                 @PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) {
        Salon salon = salonMapper.convertSalonsDtoToSalonForSalonUpdate(salonUpdate, id);
        salon = salonsModificationService.updateSalons(salon);
        return salonMapper.convertSalonToSalonsDto(salon);
    }
}