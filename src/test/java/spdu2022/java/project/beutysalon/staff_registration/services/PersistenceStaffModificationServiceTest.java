package spdu2022.java.project.beutysalon.staff_registration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.staff_registration.persistence.repositories.PersistenceStaffRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Staff Modification Service")
class PersistenceStaffModificationServiceTest {
    @Mock
    private PersistenceStaffRepository repository;
    @InjectMocks
    private PersistenceStaffModificationService modificationService;

    @Test
    @DisplayName("Method POST: If Staff exist in DB - Exception. Else return Staff with Id")
    void insertNewStaff() {
        when(repository.getCountStaffByUserId(staffExistInDb())).thenReturn(1);
        when(repository.getCountStaffByUserId(staffNotExistInDb())).thenReturn(0);
        when(repository.insertNewStaff(staffNotExistInDb())).thenReturn(staffNotExistInDb());

        Staff staff = repository.insertNewStaff(staffNotExistInDb());
        assertEquals(staff, staffNotExistInDb());

        assertThrows(EntityNotUniqException.class, () -> modificationService.insertNewStaff(staffExistInDb()), "If Staff exist in DB -> EntityNotUniqException.");

    }

    private Staff staffExistInDb() {
        Staff staff = new Staff();
        staff.setId(1);
        staff.setSalonId(1);
        staff.setUserId(1);
        return staff;
    }

    private Staff staffNotExistInDb() {
        Staff staff = new Staff();
        staff.setId(2);
        staff.setSalonId(1);
        staff.setUserId(2);
        return staff;
    }
}