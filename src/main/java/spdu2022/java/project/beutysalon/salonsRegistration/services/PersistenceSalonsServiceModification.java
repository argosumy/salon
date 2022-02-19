package spdu2022.java.project.beutysalon.salonsRegistration.services;

import org.springframework.stereotype.Service;

@Service
public class PersistenceSalonsServiceModification<T> implements SalonsServiceModification<T> {
    @Override
    public T createNewSalon(T newSalon) {
        return newSalon;
    }

    @Override
    public boolean deleteSalonById(long id) {
        return true;
    }

    @Override
    public T updateSalon(T salonUpdate) {
        return salonUpdate;
    }
}
