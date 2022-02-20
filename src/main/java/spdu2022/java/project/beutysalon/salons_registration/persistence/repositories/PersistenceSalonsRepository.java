package spdu2022.java.project.beutysalon.salons_registration.persistence.repositories;

import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.salons_registration.persistence.repositories.mappers.SalonMapperResult;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PersistenceSalonsRepository implements SalonsRepository {
    private final DataSource dataSource;

    public PersistenceSalonsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Salon> findById(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SalonsSQLQueries.SELECT_SALON_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new SalonMapperResult().mapperLight(resultSet));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Salon> getAllSalonsFromCity(String city) {
        return Collections.emptyList();
    }

    @Override
    public Salon createNewSalons(Salon newSalon) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SalonsSQLQueries.INSERT_SALON, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newSalon.getSalonName());
            preparedStatement.setString(2, newSalon.getPhone());
            preparedStatement.setString(3, newSalon.getCityLocation());
            long i = preparedStatement.executeUpdate();
            if(i == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    newSalon.setId(rs.getLong(1));
                }
            }
        }
        return newSalon;
    }

    @Override
    public boolean deleteSalonsById(long id) {
        return false;
    }

    @Override
    public Salon updateSalons(Salon entityUpdate) {
        return new Salon();
    }
}
