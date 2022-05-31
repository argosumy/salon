package spdu2022.java.project.beutysalon.usersregistration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.usersregistration.persistence.repositories.UsersRepository;

@Service
public class PersistenceUsersModificationService implements UsersModificationService {
    private final UsersRepository usersRepository;

    public PersistenceUsersModificationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User createNewUser(User newUser) {
        if (isUserExist(newUser)) {
            throw new EntityNotUniqException("User already exist by phone " + newUser.getPhone());
        }
        return usersRepository.insertNewUser(newUser);
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }

    @Override
    public User updateUserById(long id) {
        return null;
    }

    private boolean isUserExist(User newUser) {
        User user = usersRepository.findUserByPhone(newUser.getPhone());
        return user.getId() != 0;
    }
}
