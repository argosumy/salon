package spdu2022.java.project.beutysalon.salons_working_mode.persistence.mappers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingModeForDaysPeriodResultSetExtractor implements ResultSetExtractor<SalonWorkingMode> {
    @Override
    public SalonWorkingMode extractData(ResultSet rs) throws SQLException, DataAccessException {
        SalonWorkingMode result = new SalonWorkingMode();
        WorkingDayPeriod period;
        while (rs.next()) {
            period = new WorkingDayPeriod();
            if(result.getSalonId() == 0) {
                result.setSalonId(rs.getLong("salon_id"));
            }
            period.setWorkingDay(rs.getTimestamp("start_working").toLocalDateTime().toLocalDate());
            period.setStartWorking(rs.getTimestamp("start_working").toLocalDateTime().toLocalTime());
            period.setEndWorking(rs.getTimestamp("finish_working").toLocalDateTime().toLocalTime());
            result.getSalonWorkingPeriods().add(period);
        }
        return result;
    }
}
