package spdu2022.java.project.beutysalon.usersregistration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.User;

public interface UsersRepository {
    User findUserByPhone(String phone);

    User insertNewUser(User user);

    User updateUser(User user);

    boolean deleteUserById(long id);
}
