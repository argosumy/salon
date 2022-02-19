package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.salons_registration.persistence.entities.Salon;

import java.util.List;
import java.util.Optional;

@Repository
public class PersistenceSalonsRepository implements SalonsRepository {
    private JdbcTemplate jdbcTemplate;

    public PersistenceSalonsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Salon> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return null;
    }
}
