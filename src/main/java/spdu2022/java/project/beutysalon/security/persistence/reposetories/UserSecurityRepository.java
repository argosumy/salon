package spdu2022.java.project.beutysalon.security.persistence.reposetories;

import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;

public interface UserSecurityRepository {
    SecurityUser findUserByEmail(String email);
    SecurityUser findUserByLogin(String login);
}
