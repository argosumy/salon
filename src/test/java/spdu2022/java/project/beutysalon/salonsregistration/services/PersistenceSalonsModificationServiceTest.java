package spdu2022.java.project.beutysalon.salonsregistration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories.PersistenceSalonsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Salons Modification Service")
class PersistenceSalonsModificationServiceTest {
    @Mock
    private PersistenceSalonsRepository salonsRepository;
    @InjectMocks
    private PersistenceSalonsModificationService service;

    @Test
    @DisplayName("Method POST: If Salon not exist in DB - return Salon with Id")
    void createUniqueNewSalons() {
        when(salonsRepository.findByPhone(uniqueSalon().getPhone())).thenReturn(new Salon());
        when(salonsRepository.createNewSalons(uniqueSalon())).thenReturn(uniqueSalon());

        Salon result = service.createNewSalons(uniqueSalon());
        assertEquals(uniqueSalon(), result);
    }

    @Test
    @DisplayName("Method POST: If Salon exist in DB - Exception")
    void createNotUniqueNewSalons() {
        when(salonsRepository.findByPhone(salonExist().getPhone())).thenReturn(salonExist());
        assertThrows(EntityNotUniqException.class, () -> service.createNewSalons(salonExist()), "If Salon exist in DB -> EntityNotUniqException");
    }

    @Test
    void deleteSalonsById() {
    }

    @Test
    void updateSalons() {
    }

    private Salon uniqueSalon() {
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