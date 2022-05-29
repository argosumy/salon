package spdu2022.java.project.beutysalon.usersregistration.services;

import spdu2022.java.project.beutysalon.entities.User;

public interface UsersModificationService {
    User createNewUser(User user);

    boolean deleteUserById(long id);

    User updateUserById(long id);
}