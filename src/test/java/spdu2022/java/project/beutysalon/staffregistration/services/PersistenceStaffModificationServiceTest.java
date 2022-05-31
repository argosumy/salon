package spdu2022.java.project.beutysalon.staffregistration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.staffregistration.persistence.repositories.PersistenceStaffRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Staff Modification Service")
class PersistenceStaffModificationServiceTest {
    @Mock
    private PersistenceStaffRepository repository;
    @InjectMocks
    private PersistenceStaffModificationService modificationService;

    @Test
    @DisplayName("Method POST: If Staff not exist in DB - return Staff with Id")
    void insertUniqueNewStaff() {
        when(repository.getCountStaffByUserId(uniqueStaff())).thenReturn(0);
        when(repository.insertNewStaff(uniqueStaff())).thenReturn(uniqueStaff());

        Staff staff = modificationService.insertNewStaff(uniqueStaff());
        assertEquals(uniqueStaff(), staff);
    }

    @Test
    @DisplayName("Method POST: If Staff exist in DB - Exception.")
    void insertNotUniqueNewStaff() {
        when(repository.getCountStaffByUserId(staffExistInDb())).thenReturn(1);
        assertThrows(EntityNotUniqException.class, () -> modificationService.insertNewStaff(staffExistInDb()), "If Staff exist in DB -> EntityNotUniqException.");
    }

    private Staff staffExistInDb() {
        Staff staff = new Staff();
        staff.setId(1);
        staff.setSalonId(1);
        staff.setUserId(1);
        staff.setLinkPhoto("");
        return staff;
    }

    private Staff uniqueStaff() {
        Staff staff = new Staff();
        staff.setId(2);
        staff.setSalonId(1);
        staff.setUserId(2);
        staff.setLinkPhoto("");
        return staff;
    }
}