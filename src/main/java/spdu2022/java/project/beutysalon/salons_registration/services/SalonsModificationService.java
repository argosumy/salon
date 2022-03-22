package spdu2022.java.project.beutysalon.salons_registration.services;

import spdu2022.java.project.beutysalon.entities.Salon;

public interface SalonsModificationService {
    Salon createNewSalons(Salon newSalon);
    boolean deleteSalonsById(long id);
    Salon updateSalons(Salon entityUpdate);
}
