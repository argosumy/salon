package spdu2022.java.project.beutysalon.salonsRegistration.services;

import java.util.List;
import java.util.Optional;

public interface SalonServiceGet<T> {
    Optional<T> findById(long id);
    List<T> getAllSalonsFromCity(String city);
}
