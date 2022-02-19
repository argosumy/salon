package spdu2022.java.project.beutysalon.salonsRegistration.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersistenceSalonsServiceGet<T> implements SalonServiceGet<T> {
    @Override
    public Optional<T> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<T> getAllSalonsFromCity(String city) {
        return null;
    }
}
