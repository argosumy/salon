package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.util.List;
import java.util.Optional;

public interface SalonsRepository {
    Optional<Salon> findById(long id);
    List<Salon> getAllSalonsFromCity(String city);
}
