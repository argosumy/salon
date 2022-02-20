package spdu2022.java.project.beutysalon.salons_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.SalonsRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PersistenceSalonsGetService implements SalonsGetService {
    private final SalonsRepository salonsRepository;

    public PersistenceSalonsGetService(SalonsRepository salonsRepository) {
        this.salonsRepository = salonsRepository;
    }

    @Override
    public Optional<Salon> findById(long id) throws SQLException {
        return salonsRepository.findById(id);
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return salonsRepository.getAllSalonsFromCity(city);
    }
}
