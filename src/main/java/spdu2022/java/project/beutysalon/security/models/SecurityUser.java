package spdu2022.java.project.beutysalon.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class SecurityUser implements UserDetails {
    private final String login;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;
    private final Status status;
    private final Role role;

    public SecurityUser(String login, String password, Role role, Status status) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.authorities = role.getAuthorities();
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }

    public Role getRole() {
        return role;
    }

}