package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers;

import spdu2022.java.project.beutysalon.entities.Salon;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonMapperResult {
    public Salon mapperLight(ResultSet resultSet) throws SQLException {
        Salon salon = new Salon();
        salon.setId(resultSet.getLong("id"));
        salon.setSalonName(resultSet.getString("salon_name"));
        salon.setPhone(resultSet.getString("phone"));
        salon.setCityLocation(resultSet.getString("city"));
        return salon;
    }
}
