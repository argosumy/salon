package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.salonsworkingmode.persistence.mappers.WorkingModeForWeekResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class PersistenceSalonsWorkingModeForWeekRepository implements SalonWorkingDayOfWeekRepository {
    private final JdbcTemplate jdbcTemplate;
    private final int firstParam = 1;
    private final int secondParam = 2;
    private final int thirdParam = 3;
    private final int fourthParam = 4;

    public PersistenceSalonsWorkingModeForWeekRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalonWorkingMode findPeriodBySalonId(long salonId) {
        final String sql = "SELECT * FROM salons_working_week_mode WHERE salon_id = ?";
        return jdbcTemplate.query(sql, new WorkingModeForWeekResultSetExtractor(), salonId);
    }

    @Override
    public long addNewWorkingPeriodBySalonId(long salonId, WorkingDayOfWeek workingDayOfWeek) {
        final String sql = "INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES(?,?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(firstParam, salonId);
            ps.setString(secondParam, workingDayOfWeek.getDayOfWeek().name());
            ps.setString(thirdParam, workingDayOfWeek.getWorkingTimePeriod().getStartWorking().toString());
            ps.setString(fourthParam, workingDayOfWeek.getWorkingTimePeriod().getEndWorking().toString());
            return ps;
        }, holder);
        Objects.requireNonNull(holder.getKey()).intValue();
        return 1;
    }

    @Override
    public long updateWorkingPeriodBySalonId(long salonId, WorkingDayOfWeek workingDayOfWeek) {
        final String sql = "UPDATE salons_working_week_mode SET start_working = ?, end_working = ? WHERE salon_id = ? AND day_week = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(firstParam, workingDayOfWeek.getWorkingTimePeriod().getStartWorking().toString());
            ps.setString(secondParam, workingDayOfWeek.getWorkingTimePeriod().getEndWorking().toString());
            ps.setLong(thirdParam, salonId);
            ps.setString(fourthParam, (workingDayOfWeek.getDayOfWeek().name()));
            return ps;
        });
        return 1;
    }
}