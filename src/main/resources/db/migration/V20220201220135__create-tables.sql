CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    city VARCHAR(50)

);

CREATE TABLE IF NOT EXISTS user_roles
(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id int NOT NULL,
    name_role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS salons
(
    id SERIAL PRIMARY KEY NOT NULL,
    salon_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    city VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS staff
(
    id SERIAL PRIMARY KEY NOT NULL,
    salon_id INT NOT NULL,
    user_id INT NOT NULL,
    staff_foto VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS salon_working_mode
(
    salon_id INT NOT NULL,
    start_working TIMESTAMP NOT NULL,
    finish_working TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS staff_working_mode
(
    staff_id INT NOT NULL,
    working_day DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS log_book_services
(
    id SERIAL PRIMARY KEY NOT NULL,
    staff_id INT NOT NULL,
    user_id INT NOT NULL,
    start_service TIMESTAMP NOT NULL,
    finish_service TIMESTAMP NOT NULL
);

