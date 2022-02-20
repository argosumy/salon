package spdu2022.java.project.beutysalon.salons_registration.services;

import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SalonsGetService {
    Optional<Salon> findById(long id) throws SQLException;
    List<Salon> getAllSalonsFromCity(String city);
}
