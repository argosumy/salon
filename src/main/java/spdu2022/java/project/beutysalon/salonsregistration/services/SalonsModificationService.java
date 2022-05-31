package spdu2022.java.project.beutysalon.salonsregistration.services;

import spdu2022.java.project.beutysalon.entities.Salon;

public interface SalonsModificationService {
    Salon createNewSalons(Salon newSalon);

    boolean deleteSalonsById(long id);

    Salon updateSalons(Salon entityUpdate);
}