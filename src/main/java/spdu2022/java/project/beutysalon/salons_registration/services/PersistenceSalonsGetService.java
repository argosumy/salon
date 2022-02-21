package spdu2022.java.project.beutysalon.salons_registration.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersistenceSalonsGetService<T> implements SalonsGetService<T> {
    @Override
    public Optional<T> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<T> getAllSalonsFromCity(String city) {
        return null;
    }
}
