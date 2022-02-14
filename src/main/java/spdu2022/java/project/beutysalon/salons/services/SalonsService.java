package spdu2022.java.project.beutysalon.salons.services;


import java.util.List;
import java.util.Optional;

public interface SalonsService <T> {
    Optional<T> findById(long id);
    List<T> getAllSalonsFromCity(String city);
    T createNewSalon(T newSalon);
    boolean deleteSalonById(long id);
    T updateSalon(T salonUpdate);
}
