package spdu2022.java.project.beutysalon.salons_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.SalonsRepository;

@Service
public class PersistenceSalonsModificationService implements SalonsModificationService {
    private final SalonsRepository salonsRepository;

    public PersistenceSalonsModificationService(SalonsRepository salonsRepository) {
        this.salonsRepository = salonsRepository;
    }

    @Override
    public Salon createNewSalons(Salon newSalon) {
        if(isExistSalonByPhone(newSalon.getPhone())) {
            throw new EntityNotUniqException("Salon with phone " + newSalon.getPhone() + " already exist");
        }
        return salonsRepository.createNewSalons(newSalon);
    }

    @Override
    public boolean deleteSalonsById(long id) {
        return false;
    }

    @Override
    public Salon updateSalons(Salon entityUpdate) {
        return entityUpdate;
    }

    private boolean isExistSalonByPhone(String phone) {
        Salon salon = salonsRepository.findByPhone(phone);
        return salon.getId() != 0;
    }
}

