package spdu2022.java.project.beutysalon.security.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.security.models.Role;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;
import spdu2022.java.project.beutysalon.security.models.Status;
import spdu2022.java.project.beutysalon.security.persistence.reposetories.CreateFistMainAdmin;
import spdu2022.java.project.beutysalon.security.persistence.reposetories.UserSecurityRepository;

@Service
@PropertySource("classpath:security.properties")
public class RunAfterStartUp {
    private final UserSecurityRepository repository;
    private final CreateFistMainAdmin createFistMainAdminRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${security.login}")
    private String userLogin;
    @Value("${security.password}")
    private String password;

    public RunAfterStartUp(UserSecurityRepository repository, CreateFistMainAdmin createFistMainAdminRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.createFistMainAdminRepository = createFistMainAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        try {
            SecurityUser securityUser = repository.findUserByLogin(userLogin);

        } catch (UsernameNotFoundException e) {
            createUserMainAdmin();
        }
    }

    private void createUserMainAdmin() {
        final String encodedPassword = passwordEncoder.encode(password);
        final SecurityUser securityUser = new SecurityUser(userLogin, encodedPassword, Role.MAIN_ADMIN, Status.ACTIVE);
        createFistMainAdminRepository.createUserAdmin(securityUser);
    }
}
