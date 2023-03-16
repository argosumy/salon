package spdu2022.java.project.beutysalon.security.services;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;
import spdu2022.java.project.beutysalon.security.persistence.reposetories.UserSecurityRepository;

import static spdu2022.java.project.beutysalon.security.services.mappers.UserDetailsServiceMapper.fromUser;

@Service("userDetailsServiceImpl")
@Profile({"security_db","security_jwt"})
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserSecurityRepository securityRepository;

    public UserDetailsServiceImpl(UserSecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        SecurityUser user = securityRepository.findUserByLogin(login);
        return fromUser(user);
    }
}
