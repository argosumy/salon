package spdu2022.java.project.beutysalon.users_registration.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.users_registration.persistence.repositories.PersistenceUsersRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("User Modification Service")
class PersistenceUsersModificationServiceTest {
    @Mock
    private PersistenceUsersRepository repository;
    @InjectMocks
    private PersistenceUsersModificationService usersModificationService;


    @Test
    @DisplayName("Method POST: If User exist in DB - Exception. Else return User with Id")
    void createNewUser() {
       when(repository.findUserByPhone(newUserExistInDb().getPhone())).thenReturn(newUserExistInDb());
       when(repository.findUserByPhone(newUserNotExistInDb().getPhone())).thenReturn(new User());
       when(repository.insertNewUser(newUserNotExistInDb())).thenReturn(newUserNotExistInDb());

       User result = usersModificationService.createNewUser(newUserNotExistInDb());
       assertEquals(newUserNotExistInDb(), result);

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

    private User newUserNotExistInDb() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Roman");
        user.setLastName("Titov");
        user.setPhone("+380990000000");
        user.setCity("Kiev");
        return user;
    }
}