package spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.Salon;

import java.util.List;

public interface SalonsRepository {
    Salon findById(long id);

    Salon findByPhone(String phone);

    List<Salon> getAllSalonsFromCity(String city);

    Salon createNewSalons(Salon newSalon);

    boolean deleteSalonsById(long id);

    Salon updateSalons(Salon entityUpdate);
}