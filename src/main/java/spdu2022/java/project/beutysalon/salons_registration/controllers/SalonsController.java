package spdu2022.java.project.beutysalon.salons_registration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.salons_registration.dto.SalonsDTO;
import spdu2022.java.project.beutysalon.salons_registration.exeptions.Error;
import spdu2022.java.project.beutysalon.salons_registration.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.services.SalonsGetService;
import spdu2022.java.project.beutysalon.salons_registration.services.SalonsModificationService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.SQLException;
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
    public SalonsDTO getSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) throws SQLException {
        Salon salon = salonsGetService.findById(id).orElseThrow(() -> new NotFoundException("Salon not found by ID " + id));
        return salonMapper.convertSalonToSalonsDto(salon);
    }

    @GetMapping
    public List<SalonsDTO> getAllSalonsFromCity(@RequestParam String city) {
        List<Salon> salons = salonsGetService.getAllSalonsFromCity(city);
        return salonMapper.convertSalonsToListSalonsDto(salons);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalonsDTO createNewSalon(@Valid @RequestBody SalonsDTO newSalon) throws SQLException {
        Salon salon = salonMapper.convertSalonsDtoToSalonForSalonCreate(newSalon);
        salon = salonsModificationService.createNewSalons(salon);
        return salonMapper.convertSalonToSalonsDto(salon);
    }

    @DeleteMapping("/{salonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id)  {
        if(!salonsModificationService.deleteSalonsById(id)) {
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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> handleConstraintViolationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }
}
