package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers.SalonRowMapper;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers.SalonsResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

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
        final String GET_SALON_BY_PHONE = "SELECT * FROM salons WHERE phone = ?";
        return jdbcTemplate.query(GET_SALON_BY_PHONE, new SalonsResultSetExtractor(), phone);
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return jdbcTemplate.query("SELECT * FROM salons WHERE city = ?", new SalonRowMapper(), city);
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
