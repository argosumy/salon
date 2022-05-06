package spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers.WorkingModeForDaysPeriodResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class PersistenceSalonsWorkingDayModeForDaysRepository implements SalonWorkingDayModeRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceSalonsWorkingDayModeForDaysRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalonWorkingMode findPeriodBySalonId(long salonId) {
        final String FIND_SALONS_WORKING_MODES_BY_SALON_ID = "SELECT * FROM salon_working_mode WHERE start_working >= ? and salon_id = ?";
        Timestamp dateNow = Timestamp.valueOf(LocalDateTime.now());
        return jdbcTemplate.query(FIND_SALONS_WORKING_MODES_BY_SALON_ID, new WorkingModeForDaysPeriodResultSetExtractor(), dateNow, salonId);
    }

    @Override
    public long addNewWorkingPeriodBySalonId(long salonId, WorkingDay workingDay) {
        final String INSERT_NEW_WORKING_PERIOD_BY_SALON_ID = "INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES(?,?,?)";
        Timestamp startWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getStartWorking() + ":00");
        Timestamp endWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getEndWorking() + ":00");
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_NEW_WORKING_PERIOD_BY_SALON_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, salonId);
            ps.setTimestamp(2, startWorking);
            ps.setTimestamp(3, endWorking);
            return ps;
        });
        return 1;
    }

    @Override
    public long updateWorkingPeriodBySalonId(long salonId, WorkingDay workingDay) {
        final String UPDATE_WORKING_PERIOD_BY_SALON_ID = "UPDATE salon_working_mode SET start_working = ?, finish_working = ? WHERE salon_id = ? AND start_working >= ?  AND start_working < ?";
        Timestamp startWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getStartWorking() + ":00");
        Timestamp endWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getEndWorking() + ":00");
        Timestamp dateStart = Timestamp.valueOf(workingDay.getWorkingDay() + " 00:00:00");
        Timestamp dateEnd = Timestamp.valueOf(workingDay.getWorkingDay().plusDays(1) + " 00:00:00");
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_WORKING_PERIOD_BY_SALON_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, startWorking);
            ps.setTimestamp(2, endWorking);
            ps.setLong(3, salonId);
            ps.setTimestamp(4, dateStart);
            ps.setTimestamp(5, dateEnd);
            return ps;
        });
        return 1;
    }
}