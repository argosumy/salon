package spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories.mappers.SalonRowMapper;
import spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories.mappers.SalonsResultSetExtractor;

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
        final String sql = "SELECT * FROM salons WHERE phone = ?";
        return jdbcTemplate.query(sql, new SalonsResultSetExtractor(), phone);
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        final String sql = "SELECT * FROM salons WHERE city = ?";
        return jdbcTemplate.query(sql, new SalonRowMapper(), city);
    }

    @Override
    public Salon createNewSalons(Salon newSalon) {
        final String sql = "INSERT INTO salons (salon_name, phone, city) VALUES (?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        final int firstParam = 1;
        final int secondParam = 2;
        final int thirdParam = 3;
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(firstParam, newSalon.getSalonName());
            ps.setString(secondParam, newSalon.getPhone());
            ps.setString(thirdParam, newSalon.getCityLocation());
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