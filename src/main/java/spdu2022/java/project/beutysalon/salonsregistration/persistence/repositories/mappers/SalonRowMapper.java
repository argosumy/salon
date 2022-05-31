package spdu2022.java.project.beutysalon.salonsregistration.persistence.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import spdu2022.java.project.beutysalon.entities.Salon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonRowMapper implements RowMapper<Salon> {

    @Override
    public Salon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Salon salon = new Salon();
        salon.setId(resultSet.getLong("id"));
        salon.setSalonName(resultSet.getString("salon_name"));
        salon.setPhone(resultSet.getString("phone"));
        salon.setCityLocation(resultSet.getString("city"));
        return salon;
    }
}
