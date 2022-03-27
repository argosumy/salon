package spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkingModeForWeekResultSetExtractor implements ResultSetExtractor<SalonWorkingMode> {
    @Override
    public SalonWorkingMode extractData(ResultSet rs) throws SQLException, DataAccessException {
        SalonWorkingMode result = new SalonWorkingMode();
        WorkingDayOfWeekPeriod period;
        while (rs.next()) {
            period = new WorkingDayOfWeekPeriod();
            if(result.getSalonId() == 0) {
                result.setSalonId(rs.getLong("salon_id"));
            }
            period.setDayOfWeek(DayOfWeek.valueOf(rs.getString("day_week")));
            period.setStartWorking(LocalTime.parse(rs.getString("start_working")));
            period.setEndWorking(LocalTime.parse(rs.getString("end_working")));
            result.getSalonWorkingPeriods().add(period);
        }
        return result;
    }

}
