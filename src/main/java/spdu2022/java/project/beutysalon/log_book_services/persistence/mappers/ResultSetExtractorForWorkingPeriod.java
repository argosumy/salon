package spdu2022.java.project.beutysalon.log_book_services.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.WorkingDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ResultSetExtractorForWorkingPeriod implements ResultSetExtractor<Map<Long, WorkingDay>> {

    @Override
    public Map<Long, WorkingDay> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, WorkingDay> result = new HashMap<>();
        while (rs.next()) {
            WorkingDay period = new WorkingDay(LocalDate.parse(rs.getString("staff_working_day")));
            Long staffId = rs.getLong("staff_id");
            period.getWorkingTimePeriod().setStartWorking(LocalTime.parse(rs.getString("start_working"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            period.getWorkingTimePeriod().setEndWorking(LocalTime.parse(rs.getString("end_working"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            result.put(staffId, period);
        }
        return result;
    }
}
