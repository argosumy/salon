package spdu2022.java.project.beutysalon.salons_registration.services;

public interface SalonsServiceModification<T> {
    T createNewSalon(T newSalon);
    boolean deleteSalonById(long id);
    T updateSalon(T salonUpdate);
}
