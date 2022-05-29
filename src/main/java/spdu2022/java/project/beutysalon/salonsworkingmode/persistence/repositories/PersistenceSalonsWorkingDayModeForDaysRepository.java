package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.salonsworkingmode.persistence.mappers.WorkingModeForDaysPeriodResultSetExtractor;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class PersistenceSalonsWorkingDayModeForDaysRepository implements SalonWorkingDayModeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final int firstParam = 1;
    private final int secondParam = 2;
    private final int thirdParam = 3;
    private final int fourthParam = 4;
    private final int fifthParam = 5;

    public PersistenceSalonsWorkingDayModeForDaysRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalonWorkingMode findPeriodBySalonId(long salonId) {
        final String sql = "SELECT * FROM salon_working_mode WHERE start_working >= ? and salon_id = ?";
        Timestamp dateNow = Timestamp.valueOf(LocalDateTime.now());
        return jdbcTemplate.query(sql, new WorkingModeForDaysPeriodResultSetExtractor(), dateNow, salonId);
    }

    @Override
    public long addNewWorkingPeriodBySalonId(long salonId, WorkingDay workingDay) {
        final String sql = "INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES(?,?,?)";
        Timestamp startWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getStartWorking() + ":00");
        Timestamp endWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getEndWorking() + ":00");
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(firstParam, salonId);
            ps.setTimestamp(secondParam, startWorking);
            ps.setTimestamp(thirdParam, endWorking);
            return ps;
        });
        return 1;
    }

    @Override
    public long updateWorkingPeriodBySalonId(long salonId, WorkingDay workingDay) {
        final String sql = "UPDATE salon_working_mode SET start_working = ?, finish_working = ? WHERE salon_id = ? AND start_working >= ?  AND start_working < ?";
        Timestamp startWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getStartWorking() + ":00");
        Timestamp endWorking = Timestamp.valueOf(workingDay.getWorkingDay() + " " + workingDay.getWorkingTimePeriod().getEndWorking() + ":00");
        Timestamp dateStart = Timestamp.valueOf(workingDay.getWorkingDay() + " 00:00:00");
        Timestamp dateEnd = Timestamp.valueOf(workingDay.getWorkingDay().plusDays(1) + " 00:00:00");
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(firstParam, startWorking);
            ps.setTimestamp(secondParam, endWorking);
            ps.setLong(thirdParam, salonId);
            ps.setTimestamp(fourthParam, dateStart);
            ps.setTimestamp(fifthParam, dateEnd);
            return ps;
        });
        return 1;
    }
}