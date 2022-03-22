package spdu2022.java.project.beutysalon.users_registration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.users_registration.persistence.repositories.PersistenceUsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Modification Service")
class PersistenceUsersModificationServiceTest {
    @Mock
    private PersistenceUsersRepository repository;
    @InjectMocks
    private PersistenceUsersModificationService usersModificationService;

    @Test
    @DisplayName("Method POST: If User not exist in DB - return User with Id")
    void createUniqueNewUser() {
       when(repository.findUserByPhone(newUniqueUser().getPhone())).thenReturn(new User());
       when(repository.insertNewUser(newUniqueUser())).thenReturn(newUniqueUser());

       User result = usersModificationService.createNewUser(newUniqueUser());
       assertEquals(newUniqueUser(), result);
    }

    @Test
    @DisplayName("Method POST: If User exist in DB - Exception.")
    void createNotUniqueNewUser() {
        when(repository.findUserByPhone(newUserExistInDb().getPhone())).thenReturn(newUserExistInDb());

        assertThrows(EntityNotUniqException.class, () -> usersModificationService.createNewUser(newUserExistInDb()), "If User exist in DB -> EntityNotUniqException.");
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void updateUserById() {
    }

    private User newUserExistInDb() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Valerii");
        user.setLastName("Cherkasov");
        user.setPhone("+380994869938");
        user.setCity("Kiev");
        return user;
    }

    private User newUniqueUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Roman");
        user.setLastName("Titov");
        user.setPhone("+380990000000");
        user.setCity("Kiev");
        return user;
    }
}