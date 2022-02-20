package spdu2022.java.project.beutysalon.salons_registration.services;

import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.sql.SQLException;

public interface SalonsModificationService {
    Salon createNewSalons(Salon newSalon) throws SQLException;
    boolean deleteSalonsById(long id);
    Salon updateSalons(Salon entityUpdate);
}
