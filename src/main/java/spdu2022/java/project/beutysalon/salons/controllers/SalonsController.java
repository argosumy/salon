package spdu2022.java.project.beutysalon.salons.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.salons.DTO.SalonsDTOGet;
import spdu2022.java.project.beutysalon.salons.DTO.SalonsDTOModification;
import spdu2022.java.project.beutysalon.salons.exeptions.Error;
import spdu2022.java.project.beutysalon.salons.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.salons.services.SalonServiceGet;
import spdu2022.java.project.beutysalon.salons.services.SalonsServiceModification;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/salons")
@Validated
public class SalonsController {
    private final SalonsServiceModification<SalonsDTOModification> salonsServiceModification;
    private final SalonServiceGet<SalonsDTOGet> salonServiceGet;

    public SalonsController(SalonsServiceModification<SalonsDTOModification> salonsServiceModification, SalonServiceGet<SalonsDTOGet> salonServiceGet) {
        this.salonsServiceModification = salonsServiceModification;
        this.salonServiceGet = salonServiceGet;
    }

    @GetMapping("/{salonId}")
    public SalonsDTOGet getSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) {
        return salonServiceGet.findById(id).orElseThrow(() -> new NotFoundException("Salon not found by ID " + id));
    }

    @GetMapping
    public List<SalonsDTOGet> getAllSalonsFromCity(@RequestParam String city) {
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalonsDTOModification createNewSalon(@Valid @RequestBody SalonsDTOModification newSalon) {
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
    public SalonsDTOModification updateSalon(@Valid @RequestBody SalonsDTOModification salonUpdate,
                                             @PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") int id) {

        return salonsServiceModification.updateSalon(salonUpdate);
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
