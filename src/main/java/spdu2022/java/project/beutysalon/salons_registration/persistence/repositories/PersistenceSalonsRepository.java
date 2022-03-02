package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers.SalonResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class PersistenceSalonsRepository implements SalonsRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceSalonsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Salon findById(long id) {
        return new Salon();
    }

    @Override
    public Salon findByPhone(String phone) {
        final String GET_SALON_BY_PHONE = "SELECT * FROM salons WHERE phone = :phone";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("phone", phone);
        return namedParameterJdbcTemplate.query(GET_SALON_BY_PHONE, parameters, new SalonResultSetExtractor());
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return new ArrayList<>();
    }

    @Override
    public Salon createNewSalons(Salon newSalon) {
        final String INSERT_SALON = "INSERT INTO salons (salon_name, phone, city) VALUES (?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_SALON, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newSalon.getSalonName());
            ps.setString(2, newSalon.getPhone());
            ps.setString(3, newSalon.getCityLocation());
            return ps;
        }, holder);
        newSalon.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return newSalon;
    }

    @Override
    public boolean deleteSalonsById(long id) {
        return false;
    }

    @Override
    public Salon updateSalons(Salon entityUpdate) {
        return new Salon();
    }

}
