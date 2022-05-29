package spdu2022.java.project.beutysalon.usersregistration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.usersregistration.persistence.mappers.UsersResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class PersistenceUsersRepository implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final int firstParam = 1;
    private final int secondParam = 2;
    private final int thirdParam = 3;
    private final int fourthParam = 4;

    public PersistenceUsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findUserByPhone(String phone) {
        final String sql = "SELECT * FROM users WHERE phone = :phone";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.query(sql, new HashMap<String, String>() {{ put("phone", phone); }}, new UsersResultSetExtractor());
    }

    @Override
    public User insertNewUser(User user) {
        final String sql = "INSERT INTO users (first_name, last_name, phone, city) VALUES (?,?,?,?) RETURNING id";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(firstParam, user.getFirstName());
            ps.setString(secondParam, user.getLastName());
            ps.setString(thirdParam, user.getPhone());
            ps.setString(fourthParam, user.getCity());
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