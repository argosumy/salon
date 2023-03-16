package spdu2022.java.project.beutysalon.security.services.mappers;

import org.springframework.security.core.userdetails.UserDetails;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;

public class UserDetailsServiceMapper {

    public static UserDetails fromUser(SecurityUser user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), user.getRole().getAuthorities());
    }
}
