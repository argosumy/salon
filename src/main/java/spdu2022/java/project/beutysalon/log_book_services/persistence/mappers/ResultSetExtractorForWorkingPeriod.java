package spdu2022.java.project.beutysalon.log_book_services.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ResultSetExtractorForWorkingPeriod implements ResultSetExtractor<Map<Long, WorkingPeriod>> {

    @Override
    public Map<Long, WorkingPeriod> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, WorkingPeriod> result = new HashMap<>();
        while (rs.next()) {
            WorkingDayPeriod period = new WorkingDayPeriod();
            Long staffId = rs.getLong("staff_id");
            period.setWorkingDay(LocalDate.parse(rs.getString("staff_working_day")));
            period.setStartWorking(rs.getString("start_working"));
            period.setEndWorking(rs.getString("end_working"));
            result.put(staffId, period);
        }
        return result;
    }
}
