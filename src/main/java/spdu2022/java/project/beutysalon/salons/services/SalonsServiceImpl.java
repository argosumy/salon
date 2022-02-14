package spdu2022.java.project.beutysalon.salons.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SalonsServiceImpl<T> implements SalonsService<T> {
    @Override
    public Optional<T> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<T> getAllSalonsFromCity(String city) {
        return new ArrayList<>();
    }

    @Override
    public T createNewSalon(T newSalon) {
        return newSalon;
    }

    @Override
    public boolean deleteSalonById(long id) {
        return false;
    }

    @Override
    public T updateSalon(T salonUpdate) {
        return salonUpdate;
    }
}
