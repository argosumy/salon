package spdu2022.java.project.beutysalon.salons_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.SalonsRepository;

import java.util.List;

@Service
public class PersistenceSalonsGetService implements SalonsGetService {
    private final SalonsRepository salonsRepository;

    public PersistenceSalonsGetService(SalonsRepository salonsRepository) {
        this.salonsRepository = salonsRepository;
    }

    @Override
    public Salon findById(long id) {
        return salonsRepository.findById(id);
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return salonsRepository.getAllSalonsFromCity(city);
    }
}
