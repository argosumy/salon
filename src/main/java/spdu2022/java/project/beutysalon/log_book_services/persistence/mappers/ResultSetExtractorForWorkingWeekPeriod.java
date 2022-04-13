package spdu2022.java.project.beutysalon.log_book_services.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.WorkingDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalDate;
import static spdu2022.java.project.beutysalon.utils.LocaleDateTimeConverter.convertToLocalTime;

public class ResultSetExtractorForWorkingWeekPeriod implements ResultSetExtractor<Map<Long, WorkingDay>> {
    @Override
    public Map<Long, WorkingDay> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, WorkingDay> result = new HashMap<>();
        while (rs.next()) {
            WorkingDay period = new WorkingDay(convertToLocalDate(rs.getString("staff_working_day")));
            Long staffId = rs.getLong("staff_id");
            period.addWorkingTimePeriod(convertToLocalTime(rs.getString("start_working"), "HH:mm"),
                                        convertToLocalTime(rs.getString("end_working"), "HH:mm"));
            result.put(staffId, period);
        }
        return result;
    }
}
