package spdu2022.java.project.beutysalon.notification.persistence.mappers;

import org.springframework.jdbc.core.RowMapper;
import spdu2022.java.project.beutysalon.entities.*;
import spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookedServicesMapper implements RowMapper<BookedService> {
    @Override
    public BookedService mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Salon salon = new Salon();
        salon.setId(rs.getLong("salon_id"));
        salon.setSalonName(rs.getString("salon_name"));
        salon.setPhone(rs.getString("salon_phone"));
//        salon.setCityLocation(rs.getString("salons.city"));

        final Staff staff = new Staff();
        staff.setId(rs.getLong("staff_id"));
        staff.setSalonId(rs.getLong("salon_id"));

        final User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhone(rs.getString("user_phone"));
        user.setEmail(rs.getString("email"));

        final WorkingDay bookingDay = new WorkingDay();
        Timestamp start = rs.getTimestamp("start_service");
        Timestamp finish = rs.getTimestamp("finish_service");
        bookingDay.setWorkingDay(LocaleDateTimeConverter.convertToLocalDate(start));
        bookingDay.addWorkingTimePeriod(LocaleDateTimeConverter.convertToLocalTime(start),
                LocaleDateTimeConverter.convertToLocalTime(finish));
        return new BookedService(salon, staff, user, bookingDay);
    }
}
