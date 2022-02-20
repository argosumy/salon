package spdu2022.java.project.beutysalon.users_registration.persistence.repositories;

class UsersSQLQueries {
    static final String INSERT_USER = "INSERT INTO users (first_name, last_name, phone, city) VALUES (?,?,?,?) RETURNING id";
}
