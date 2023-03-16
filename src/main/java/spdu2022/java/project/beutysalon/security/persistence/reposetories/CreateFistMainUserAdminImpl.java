package spdu2022.java.project.beutysalon.security.persistence.reposetories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spdu2022.java.project.beutysalon.security.models.SecurityUser;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class CreateFistMainUserAdminImpl implements CreateFistMainAdmin {
    private final JdbcTemplate jdbcTemplate;

    public CreateFistMainUserAdminImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public long createUserAdmin(SecurityUser securityUser) {
        final String createUser = "INSERT INTO users(first_name, last_name, phone) VALUES ('APP_ADMIN','APP_ADMIN', 0) RETURNING id";
        final String createSecurityUser = "INSERT INTO user_roles(user_id, name_role, password, login) VALUES (?,?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> con.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS), holder);
        long id = (Objects.requireNonNull(holder.getKey()).intValue());
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(createSecurityUser, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);
            ps.setString(2, securityUser.getRole().name());
            ps.setString(3, securityUser.getPassword());
            ps.setString(4, securityUser.getUsername());
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }
}