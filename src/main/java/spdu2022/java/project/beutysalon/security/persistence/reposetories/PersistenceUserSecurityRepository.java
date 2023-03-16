package spdu2022.java.project.beutysalon.security.persistence.reposetories;

import nonapi.io.github.classgraph.utils.Join;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.security.models.Role;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;
import spdu2022.java.project.beutysalon.security.models.Status;


@Repository
public class PersistenceUserSecurityRepository implements UserSecurityRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceUserSecurityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SecurityUser findUserByLogin(String login) {
        final String sql = "SELECT * FROM user_roles WHERE login = ?";
        SecurityUser securityUser = jdbcTemplate.query(sql, rs -> {
            if(rs.next()) {
                return new SecurityUser(
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("name_role")),
                        Status.valueOf(rs.getString("status"))
                );
            }
            throw new UsernameNotFoundException(Join.join("User with email ",login, " not found"));
        }, login);
        return securityUser;
    }

    @Override
    public SecurityUser findUserByEmail(String email) throws UsernameNotFoundException {
        final String sql = "SELECT * FROM users INNER JOIN user_roles ON users.id = user_roles.user_id WHERE email = ?";
        SecurityUser securityUser = jdbcTemplate.query(sql, rs -> {
            if(rs.next()) {
                return new SecurityUser(
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("name_role")),
                        Status.valueOf(rs.getString("status"))
                );
            }
            throw new UsernameNotFoundException(Join.join("User with email ",email, " not found"));
        }, email);
        return securityUser;
    }

}
