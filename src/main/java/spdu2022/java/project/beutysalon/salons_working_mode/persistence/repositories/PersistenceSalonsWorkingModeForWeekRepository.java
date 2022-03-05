package spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers.WorkingModeForWeekResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository(value = "weekPeriod")
public class PersistenceSalonsWorkingModeForWeekRepository implements SalonWorkingModeRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceSalonsWorkingModeForWeekRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalonWorkingMode findPeriodBySalonId(long salonId) {
        final String FIND_WORKING_MODE_FOR_WEEK_BY_SALON_ID = "SELECT * FROM salons_working_week_mode WHERE salon_id = ?";
        return jdbcTemplate.query(FIND_WORKING_MODE_FOR_WEEK_BY_SALON_ID, new WorkingModeForWeekResultSetExtractor(), salonId);
    }

    @Override
    public long addNewWorkingPeriodBySalonId(long salonId, WorkingPeriod period) {
        WorkingDayOfWeekPeriod weekPeriod = (WorkingDayOfWeekPeriod)period;
        final String INSERT_NEW_WORKING_PERIOD_BY_SALON_ID = "INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES(?,?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_NEW_WORKING_PERIOD_BY_SALON_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, salonId);
            ps.setString(2, weekPeriod.getDaysOfWeek().name());
            ps.setString(3, weekPeriod.getStartWorking());
            ps.setString(4, weekPeriod.getEndWorking());
            return ps;
        }, holder);
        Objects.requireNonNull(holder.getKey()).intValue();
        return 0;
    }

    @Override
    public long updateWorkingPeriodBySalonId(long salonId, WorkingPeriod period) {
        WorkingDayOfWeekPeriod weekPeriod = (WorkingDayOfWeekPeriod)period;
        final String UPDATE_WORKING_PERIOD_BY_SALON_ID = "UPDATE salons_working_week_mode SET start_working = ?, end_working = ? WHERE salon_id = ? AND day_week = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_WORKING_PERIOD_BY_SALON_ID);
            ps.setString(1, period.getStartWorking());
            ps.setString(2, period.getEndWorking());
            ps.setLong(3, salonId);
            ps.setString(4, (weekPeriod.getDaysOfWeek().name()));
            return ps;
        });
        return 1;
    }
}
