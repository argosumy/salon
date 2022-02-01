CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    phone VARCHAR(25) NOT NULL UNIQUE,
    location_id INT,
    role_id INT NOT NULL,
    link_foto VARCHAR (300)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id SERIAL PRIMARY KEY NOT NULL,
    role_type VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS locations
(
    id SERIAL PRIMARY KEY NOT NULL,
    sity VARCHAR (50) NOT NULL,
    country_cod INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS salons
(
    id SERIAL PRIMARY KEY NOT NULL,
    salon_name VARCHAR(100) NOT NULL,
    phone VARCHAR(25) NOT NULL UNIQUE,
    location_id INT
);

CREATE TABLE IF NOT EXISTS staff
(
    salon_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS services
(
    id SERIAL PRIMARY KEY NOT NULL,
    service_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS salon_services
(
    service_id INT NOT NULL,
    salon_id INT NOT NULL,
    cost int
);

CREATE TABLE IF NOT EXISTS salon_services
(
    service_id INT NOT NULL,
    salon_id INT NOT NULL,
    cost int
);

CREATE TABLE IF NOT EXISTS staff_services
(
    service_id INT NOT NULL,
    user_id INT NOT NULL,
    service_duration TIMESTAMP
);

CREATE TABLE IF NOT EXISTS service_working_mode
(
    staff_id INT NOT NULL,
    customer_id INT NOT NULL,
    service_id INT NOT NULL,
    start_service TIMESTAMP NOT NULL,
    finish_service TIMESTAMP NOT NULL
);