package spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;
import spdu2022.java.project.beutysalon.entities.enums.DaysOfWeek;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            period.setDaysOfWeek(DaysOfWeek.valueOf(rs.getString("day_week")));
            period.setStartWorking(rs.getString("start_working"));
            period.setEndWorking(rs.getString("end_working"));
            result.getSalonWorkingPeriods().add(period);
        }
        return result;
    }

}
