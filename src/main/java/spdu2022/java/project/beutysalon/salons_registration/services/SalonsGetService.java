package spdu2022.java.project.beutysalon.salons_registration.services;

import java.util.List;
import java.util.Optional;

public interface SalonsGetService<T> {
    Optional<T> findById(long id);
    List<T> getAllSalonsFromCity(String city);
}
