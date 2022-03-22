package spdu2022.java.project.beutysalon.salons_registration.services;

import spdu2022.java.project.beutysalon.entities.Salon;

import java.util.List;

public interface SalonsGetService {
    Salon findById(long id);
    List<Salon> getAllSalonsFromCity(String city);
}
