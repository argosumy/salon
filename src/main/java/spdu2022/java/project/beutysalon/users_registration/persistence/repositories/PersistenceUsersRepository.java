package spdu2022.java.project.beutysalon.users_registration.persistence.repositories;

import org.springframework.stereotype.Repository;
import spdu2022.java.project.beutysalon.entities.User;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PersistenceUsersRepository implements UsersRepository {
    private final DataSource dataSource;

    public PersistenceUsersRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User insertNewUser(User user) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement prStatement = connection.prepareStatement(UsersSQLQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            prStatement.setString(1, user.getFirstName());
            prStatement.setString(2, user.getLastName());
            prStatement.setString(3, user.getPhone());
            prStatement.setString(4, user.getCity());
            int i = prStatement.executeUpdate();
            if(i == 1) {
                ResultSet rs = prStatement.getGeneratedKeys();
                if(rs.next()) {
                    long userId = rs.getInt(1);
                    user.setId(userId);
                }
            }
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
