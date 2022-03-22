package spdu2022.java.project.beutysalon.users_registration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.users_registration.persistence.mappers.UsersResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class PersistenceUsersRepository implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceUsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByPhone(String phone) {
        final String FIND_USER_BY_PHONE = "SELECT * FROM users WHERE phone = :phone";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.query(FIND_USER_BY_PHONE, new HashMap<String,String>(){{put("phone", phone);}}, new UsersResultSetExtractor());
    }

    @Override
    public User insertNewUser(User user) {
        final String INSERT_USER = "INSERT INTO users (first_name, last_name, phone, city) VALUES (?,?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getCity());
            return ps;
        }, holder);
        user.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return user;
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
