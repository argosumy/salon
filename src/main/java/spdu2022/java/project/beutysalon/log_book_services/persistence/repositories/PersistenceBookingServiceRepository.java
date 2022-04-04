package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.LogService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Objects;

@Repository
public class PersistenceBookingServiceRepository implements BookingServiceRepository{
    private final JdbcTemplate jdbcTemplate;

    public PersistenceBookingServiceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long bookingService(LogService bookingService) {
        String query = "INSERT INTO log_book_services (staff_id, user_id, start_service, finish_service) VALUES (?,?,?,?) RETURNING id";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
           Timestamp start = Timestamp.valueOf(bookingService.getWorkingDayPeriod().getWorkingDay().toString() + " " + bookingService.getWorkingDayPeriod().getWorkingTimePeriod().getStartWorking() + ":00.0");
           Timestamp end = Timestamp.valueOf(bookingService.getWorkingDayPeriod().getWorkingDay().toString() + " " + bookingService.getWorkingDayPeriod().getWorkingTimePeriod().getEndWorking() + ":00.0");

           PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           ps.setLong(1, bookingService.getStaffId());
           ps.setLong(2, bookingService.getUserId());
           ps.setTimestamp(3, start);
           ps.setTimestamp(4, end);

           return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }
}
