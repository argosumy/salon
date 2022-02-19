package spdu2022.java.project.beutysalon.salons_registration.services;

import org.springframework.stereotype.Service;

@Service
public class PersistenceSalonsModificationService<T> implements SalonsModificationService<T> {
    @Override
    public T createNewSalons(T newSalon) {
        return null;
    }

    @Override
    public boolean deleteSalonsById(long id) {
        return false;
    }

    @Override
    public T updateSalons(T entityUpdate) {
        return null;
    }
}

