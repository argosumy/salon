package spdu2022.java.project.beutysalon.logbookservices.persistence.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.BookedService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToTimestamp;

@Repository
public class BookingServiceJdbcRepository implements BookingServiceRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookingServiceJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long bookingService(BookedService bookingService) {
        String query = "INSERT INTO log_book_services (staff_id, user_id, start_service, finish_service) VALUES (?,?,?,?) RETURNING id";
        final int firstParam = 1;
        final int secondParam = 2;
        final int thirdParam = 3;
        final int fourthParam = 4;

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
           Timestamp start = getTimestampPeriodMap(bookingService).get("start");
           Timestamp end = getTimestampPeriodMap(bookingService).get("end");
           PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
           ps.setLong(firstParam, bookingService.getStaffId());
           ps.setLong(secondParam, bookingService.getUserId());
           ps.setTimestamp(thirdParam, start);
           ps.setTimestamp(fourthParam, end);

           return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    private Map<String, Timestamp> getTimestampPeriodMap(BookedService bookedService) {
        LocalDate workingDay = bookedService.getWorkingDay();
        LocalTime startLocalTime = bookedService.getWorkingTimePeriod().getStartWorking();
        LocalTime endLocalTime = bookedService.getWorkingTimePeriod().getEndWorking();
        return Map.of("start", convertToTimestamp(workingDay, startLocalTime),
                      "end", convertToTimestamp(workingDay, endLocalTime));
    }
}
