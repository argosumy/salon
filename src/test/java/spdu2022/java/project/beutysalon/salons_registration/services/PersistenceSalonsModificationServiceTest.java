package spdu2022.java.project.beutysalon.salons_registration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.PersistenceSalonsRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Salons Modification Service")
class PersistenceSalonsModificationServiceTest {
    @Mock
    private PersistenceSalonsRepository salonsRepository;
    @InjectMocks
    private PersistenceSalonsModificationService service;


    @Test
    @DisplayName("Method POST: If Salon exist in DB - Exception. Else return Salon with Id")
    void createNewSalons() {
        when(salonsRepository.findByPhone(salonExist().getPhone())).thenReturn(salonExist());
        when(salonsRepository.findByPhone(newSalonNotExist().getPhone())).thenReturn(new Salon());
        when(salonsRepository.createNewSalons(newSalonNotExist())).thenReturn(newSalonNotExist());

        Salon result = service.createNewSalons(newSalonNotExist());

        assertEquals(newSalonNotExist(), result);
        assertThrows(EntityNotUniqException.class, () -> service.createNewSalons(salonExist()), "If Salon exist in DB -> EntityNotUniqException");
    }

    @Test
    void deleteSalonsById() {
    }

    @Test
    void updateSalons() {
    }

    private Salon newSalonNotExist() {
        Salon salon = new Salon();
        salon.setId(1);
        salon.setSalonName("Kiki");
        salon.setCityLocation("Sumy");
        salon.setPhone("+380990000000");
        return salon;
    }

    private Salon salonExist() {
        Salon salon = new Salon();
        salon.setId(1);
        salon.setSalonName("Ludmila");
        salon.setCityLocation("Sumy");
        salon.setPhone("+380994869938");
        return salon;
    }

}