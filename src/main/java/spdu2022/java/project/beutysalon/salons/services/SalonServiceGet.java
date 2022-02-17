package spdu2022.java.project.beutysalon.salons.services;

import java.util.List;
import java.util.Optional;

public interface SalonServiceGet<T> {
    Optional<T> findById(long id);
    List<T> getAllSalonsFromCity(String city);
}
