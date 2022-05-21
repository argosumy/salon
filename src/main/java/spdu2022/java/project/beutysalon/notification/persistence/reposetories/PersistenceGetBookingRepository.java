package spdu2022.java.project.beutysalon.notification.persistence.reposetories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.persistence.mappers.BookedServicesMapper;

import java.util.List;

@Repository
public class PersistenceGetBookingRepository implements GetBookingServiceRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersistenceGetBookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BookedService> getBookingBySalonId(long salonId) {
        final String sql = """
                SELECT salon_id, salon_name, salons.phone AS salon_phone,
                       staff_id, booking.user_id AS user_id, first_name,
                       last_name, users.phone AS user_phone, email, start_service, finish_service
                FROM log_book_services AS booking
                    INNER JOIN staff ON booking.staff_id = staff.id
                    INNER JOIN users ON users.id = booking.user_id
                    INNER JOIN salons ON staff.salon_id = salons.id
                WHERE salon_id = ?
                """;
        return jdbcTemplate.query(sql, ps -> ps.setLong(1, salonId), new BookedServicesMapper());
    }
}
