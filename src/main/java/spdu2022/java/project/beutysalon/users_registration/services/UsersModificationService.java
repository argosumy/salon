package spdu2022.java.project.beutysalon.users_registration.services;

import spdu2022.java.project.beutysalon.entities.User;

import java.sql.SQLException;

public interface UsersModificationService {
    User createNewUser(User user) throws SQLException;
    boolean deleteUserById(long id);
    User updateUserById(long id);
}
