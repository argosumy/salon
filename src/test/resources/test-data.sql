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

CREATE TABLE IF NOT EXISTS salons_working_week_mode
(
    id SERIAL PRIMARY KEY NOT NULL,
    salon_id INT NOT NULL,
    day_week VARCHAR(10) NOT NULL,
    start_working VARCHAR(5) NOT NULL,
    end_working VARCHAR(5) NOT NULL
);


--INSERT salons
INSERT INTO salons(salon_name, phone, city) VALUES ('Ludmila','+380994869938','Sumy');
INSERT INTO salons(salon_name, phone, city) VALUES ('Kiki', '+380957960840', 'Sumy');
INSERT INTO salons(salon_name, phone, city) VALUES ('Woman', '+380664869938', 'Kiev');
--INSERT users
INSERT INTO users(first_name, last_name, phone, city) VALUES ('Natali', 'Zaiceva', '+380669859090', 'Sumy');
INSERT INTO users(first_name, last_name, phone, city) VALUES ('Ludmila', 'Onopko', '+380502169357', 'Sumy');
INSERT INTO users(first_name, last_name, phone, city) VALUES ('Kovalevska', 'Galina', '+380952106585', 'Sumy');
INSERT INTO users(first_name, last_name, phone, city) VALUES ('Svetlana', 'Otenko', '+380952081444', 'Kiev');
INSERT INTO users(first_name, last_name, phone, city) VALUES ('Ludmila', 'Dubovik', '+380957960841', 'Sumy');
--INSERT staff
INSERT INTO staff(salon_id, user_id) VALUES (1, 1);
INSERT INTO staff(salon_id, user_id) VALUES (1, 2);
INSERT INTO staff(salon_id, user_id) VALUES (2, 3);
INSERT INTO staff(salon_id, user_id) VALUES (3, 4);
--INSERT salon uniq working mode
INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES (1, '2023-05-01 09:00:00.000000', '2023-05-01 15:00:00.000000');
INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES (1, '2023-05-09 09:00:00.000000', '2023-05-09 17:00:00.000000');
INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES (2, '2023-05-01 09:00:00.000000', '2023-05-01 17:00:00.000000');
--INSERT salon working mode for day of week
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'MONDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'TUESDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'WEDNESDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'THURSDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'FRIDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'SATURDAY', '09:00', '19:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'SUNDAY', '09:00', '19:00');

INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'MONDAY', '08:00', '20:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'TUESDAY', '08:00', '20:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'WEDNESDAY', '08:00', '20:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'THURSDAY', '08:00', '20:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'FRIDAY', '08:00', '20:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'SATURDAY', '10:00', '18:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'SUNDAY', '10:00', '17:00');

INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (3, 'SATURDAY', '08:00', '22:00');
INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (3, 'SUNDAY', '08:00', '18:00');
--INSERT staff working mode
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-01');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-02');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-05');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-06');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-09');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-10');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-13');

INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-01');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-02');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-03');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-04');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-05');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-08');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-10');

INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-01');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-02');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-03');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-04');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-05');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-06');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-07');

INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-04-30');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-01');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-07');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-14');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-08');
INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-15');
--INSERT log book services
INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (1, 5, '2023-05-01 10:00:00.000000', '2023-05-01 12:00:00.000000');
INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (2, 4, '2023-05-01 10:00:00.000000', '2023-05-01 11:00:00.000000');
INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (3, 4, '2023-05-01 16:00:00.000000', '2023-05-01 18:00:00.000000');
INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (1, 2, '2023-05-05 12:00:00.000000', '2023-05-05 14:00:00.000000');

