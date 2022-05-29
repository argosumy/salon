package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkingModeForWeekResultSetExtractor implements ResultSetExtractor<SalonWorkingMode> {
    @Override
    public SalonWorkingMode extractData(ResultSet rs) throws SQLException, DataAccessException {
        SalonWorkingMode result = new SalonWorkingMode();
        WorkingDayOfWeek period;
        while (rs.next()) {
            period = new WorkingDayOfWeek(DayOfWeek.valueOf(rs.getString("day_week")));
            if (result.getSalonId() == 0) {
                result.setSalonId(rs.getLong("salon_id"));
            }
            period.getWorkingTimePeriod().setStartWorking(LocalTime.parse(rs.getString("start_working")));
            period.getWorkingTimePeriod().setEndWorking(LocalTime.parse(rs.getString("end_working")));
            result.getSalonsWorkingDaysOfWeek().add(period);
        }
        return result;
    }
}