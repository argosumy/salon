package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SalonsRepository {
    Optional<Salon> findById(long id) throws SQLException;
    List<Salon> getAllSalonsFromCity(String city);
    Salon createNewSalons(Salon newSalon) throws SQLException;
    boolean deleteSalonsById(long id);
    Salon updateSalons(Salon entityUpdate);
}
