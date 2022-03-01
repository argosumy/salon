package spdu2022.java.project.beutysalon.users_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.users_registration.persistence.repositories.UsersRepository;

@Service
public class PersistenceUsersModificationService implements UsersModificationService {
    private final UsersRepository usersRepository;

    public PersistenceUsersModificationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User createNewUser(User user) {
        return usersRepository.insertNewUser(user);
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }

    @Override
    public User updateUserById(long id) {
        return null;
    }
}
