package spdu2022.java.project.beutysalon.salons.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.salons.DTO.SalonsDTO;
import spdu2022.java.project.beutysalon.salons.exeptions.Error;
import spdu2022.java.project.beutysalon.salons.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.salons.services.SalonsService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

@RestController
@RequestMapping("api/v1/salons")
@Validated
public class SalonsController {
    private final SalonsService<SalonsDTO> salonsService;

    public SalonsController(SalonsService<SalonsDTO> salonsService) {
        this.salonsService = salonsService;
    }

    @GetMapping("/{salonId}")
    public SalonsDTO getSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id) {
        return salonsService.findById(id).orElseThrow(() -> new NotFoundException("Salon not found by ID " + id));
    }

    @GetMapping
    public List<SalonsDTO> getAllSalonsFromCity(@RequestParam String city) {
        return salonsService.getAllSalonsFromCity(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalonsDTO createNewSalon(@Valid @RequestBody SalonsDTO newSalon) {
        return salonsService.createNewSalon(newSalon);
    }

    @DeleteMapping("/{salonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalonById(@PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") long id)  {
        if(!salonsService.deleteSalonById(id)) {
            throw new NotFoundException("Salon not found by ID " + id);
        }
    }

    @PutMapping("/{salonId}")
    @ResponseStatus(HttpStatus.OK)
    public SalonsDTO updateSalon(@Valid @RequestBody SalonsDTO salonUpdate,
                                 @PathVariable("salonId") @Min(value = 1, message = "id must be  > 0") int id) {

        return salonsService.updateSalon(salonUpdate);
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
