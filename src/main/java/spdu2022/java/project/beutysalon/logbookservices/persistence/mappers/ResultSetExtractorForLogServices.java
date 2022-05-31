package spdu2022.java.project.beutysalon.logbookservices.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.BookedService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalDate;
import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalTime;

public class ResultSetExtractorForLogServices implements ResultSetExtractor<List<BookedService>> {
    @Override
    public List<BookedService> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<BookedService> result = new ArrayList<>();
        BookedService bookedService;
        while (rs.next()) {
            long salonId = rs.getLong("salon_id");
            long staffId = rs.getLong("staff_id");
            long userId = rs.getLong("user_id");
            Timestamp start = rs.getTimestamp("start_service");
            Timestamp end = rs.getTimestamp("end_service");
            bookedService = new BookedService(userId, convertToLocalDate(start));
            bookedService.setSalonId(salonId);
            bookedService.setStaffId(staffId);
            bookedService.addWorkingTimePeriodForDay(convertToLocalTime(start), convertToLocalTime(end));
            result.add(bookedService);
        }

        result.sort(Comparator.comparing(BookedService::getStaffId)
                .thenComparing(x -> x.getWorkingDayPeriod().getWorkingDay())
                .thenComparing(x -> x.getWorkingDayPeriod().getWorkingTimePeriod().getStartWorking()));
        return result;
    }
}
