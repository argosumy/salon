package spdu2022.java.project.beutysalon.users_registration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.User;

import java.sql.SQLException;

public interface UsersRepository {
    User insertNewUser(User user) throws SQLException;
    User updateUser(User user);
    boolean deleteUserById(long id);
}
