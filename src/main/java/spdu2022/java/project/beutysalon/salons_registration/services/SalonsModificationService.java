package spdu2022.java.project.beutysalon.salons_registration.services;

public interface SalonsModificationService<T> {
    T createNewSalons(T newSalon);
    boolean deleteSalonsById(long id);
    T updateSalons(T entityUpdate);
}
