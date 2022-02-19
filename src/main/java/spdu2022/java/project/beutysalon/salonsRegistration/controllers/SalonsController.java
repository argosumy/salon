package spdu2022.java.project.beutysalon.salonsRegistration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.salonsRegistration.dto.SalonsDTO;
import spdu2022.java.project.beutysalon.salonsRegistration.exeptions.Error;
import spdu2022.java.project.beutysalon.salonsRegistration.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.salonsRegistration.services.SalonServiceGet;
import spdu2022.java.project.beutysalon.salonsRegistration.services.SalonsServiceModification;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/salons")
@Validated
public class SalonsController {
    private final SalonsServiceModification<SalonsDTO> salonsServiceModification;
    private final SalonServiceGet<SalonsDTO> salonServiceGet;

    public SalonsController(SalonsServiceModification<SalonsDTO> salonsServiceModification, SalonServiceGet<SalonsDTO> salonServiceGet) {
        this.salonsServiceModification = salonsServiceModification;
        this.salonServiceGet = salonServiceGet;
    }

    @GetMapping("/{salonId}")
    public SalonsDTO getSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) {
        return salonServiceGet.findById(id).orElseThrow(() -> new NotFoundException("Salon not found by ID " + id));
    }

    @GetMapping
    public List<SalonsDTO> getAllSalonsFromCity(@RequestParam String city) {
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalonsDTO createNewSalon(@Valid @RequestBody SalonsDTO newSalon) {
        return salonsServiceModification.createNewSalon(newSalon);
    }

    @DeleteMapping("/{salonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id)  {
        if(!salonsServiceModification.deleteSalonById(id)) {
            throw new NotFoundException("Salon not found by ID " + id);
        }
    }

    @PutMapping("/{salonId}")
    @ResponseStatus(HttpStatus.OK)
    public SalonsDTO updateSalon(@Valid @RequestBody SalonsDTO salonUpdate,
                                 @PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") int id) {

        return salonsServiceModification.updateSalon(salonUpdate);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> notFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public ResponseEntity<Error> handleConstraintViolationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }
}
