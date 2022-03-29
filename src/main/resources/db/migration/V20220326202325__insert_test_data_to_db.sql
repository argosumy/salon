DO
$do$
    BEGIN
    --INSERT salons
        IF NOT EXISTS (SELECT * FROM salons WHERE phone = '+380994869938') THEN
            INSERT INTO salons(salon_name, phone, city) VALUES ('Ludmila','+380994869938','Sumy');
        END IF;
        IF NOT EXISTS (SELECT * FROM salons WHERE phone = '+380957960840') THEN
            INSERT INTO salons(salon_name, phone, city) VALUES ('Kiki', '+380957960840', 'Sumy');
        END IF;
        IF NOT EXISTS (SELECT * FROM salons WHERE phone = '+380664869938') THEN
           INSERT INTO salons(salon_name, phone, city) VALUES ('Woman', '+380664869938', 'Kiev');
        END IF;
    --INSERT users
        IF NOT EXISTS (SELECT * FROM users WHERE phone = '+380669859090') THEN
           INSERT INTO users(first_name, last_name, phone, city) VALUES ('Natali', 'Zaiceva', '+380669859090', 'Sumy');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE phone = '+380502169357') THEN
           INSERT INTO users(first_name, last_name, phone, city) VALUES ('Ludmila', 'Onopko', '+380502169357', 'Sumy');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE phone = '+380952106585' ) THEN
           INSERT INTO users(first_name, last_name, phone, city) VALUES ('Kovalevska', 'Galina', '+380952106585', 'Sumy');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE phone = '+380952081444' ) THEN
           INSERT INTO users(first_name, last_name, phone, city) VALUES ('Svetlana', 'Otenko', '+380952081444', 'Kiev');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE phone = '+380957960841' ) THEN
           INSERT INTO users(first_name, last_name, phone, city) VALUES ('Ludmila', 'Dubovik', '+380957960841', 'Sumy');
        END IF;
    --INSERT staff
        IF NOT EXISTS (SELECT * FROM staff WHERE user_id = 1) THEN
           INSERT INTO staff(salon_id, user_id) VALUES (1, 1);
        END IF;
        IF NOT EXISTS (SELECT * FROM staff WHERE user_id = 2) THEN
           INSERT INTO staff(salon_id, user_id) VALUES (1, 2);
        END IF;
        IF NOT EXISTS (SELECT * FROM staff WHERE user_id = 3) THEN
           INSERT INTO staff(salon_id, user_id) VALUES (2, 3);
        END IF;
        IF NOT EXISTS (SELECT * FROM staff WHERE user_id = 4) THEN
           INSERT INTO staff(salon_id, user_id) VALUES (3, 4);
        END IF;
    --INSERT salon uniq working mode
        IF NOT EXISTS (SELECT * FROM salon_working_mode WHERE start_working = '2023-05-01 09:00:00.000000' AND salon_id = 1) THEN
           INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES (1, '2023-05-01 09:00:00.000000', '2023-05-01 15:00:00.000000');
        END IF;
        IF NOT EXISTS (SELECT * FROM salon_working_mode WHERE start_working = '2023-05-09 09:00:00.000000' AND salon_id = 1) THEN
           INSERT INTO salon_working_mode(salon_id, start_working, finish_working) VALUES (1, '2023-05-09 09:00:00.000000', '2023-05-09 17:00:00.000000');
        END IF;
    --INSERT salon working mode for day of week
        IF NOT EXISTS (SELECT * FROM salons_working_week_mode WHERE salon_id = 1 AND day_week = 'MONDAY') THEN
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'MONDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'TUESDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'WEDNESDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'THURSDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'FRIDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'SATURDAY', '09:00', '19:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (1, 'SUNDAY', '09:00', '19:00');
        END IF;
        IF NOT EXISTS (SELECT * FROM salons_working_week_mode WHERE salon_id = 2 AND day_week = 'MONDAY') THEN
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'MONDAY', '08:00', '20:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'TUESDAY', '08:00', '20:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'WEDNESDAY', '08:00', '20:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'THURSDAY', '08:00', '20:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'FRIDAY', '08:00', '20:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'SATURDAY', '10:00', '18:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (2, 'SUNDAY', '10:00', '17:00');
        END IF;
        IF NOT EXISTS (SELECT * FROM salons_working_week_mode WHERE salon_id = 3 AND day_week = 'SATURDAY') THEN
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (3, 'SATURDAY', '08:00', '22:00');
            INSERT INTO salons_working_week_mode(salon_id, day_week, start_working, end_working) VALUES (3, 'SUNDAY', '08:00', '18:00');
        END IF;
    --INSERT staff working mode
        IF NOT EXISTS (SELECT * FROM staff_working_mode WHERE staff_id = 1) THEN
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-01');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-02');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-05');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-06');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-09');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-10');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (1, '2023-05-13');
        END IF;
        IF NOT EXISTS (SELECT * FROM staff_working_mode WHERE staff_id = 2) THEN
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-01');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-02');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-03');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-04');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-05');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-08');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (2, '2023-05-10');
        END IF;
        IF NOT EXISTS (SELECT * FROM staff_working_mode WHERE staff_id = 3) THEN
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-01');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-02');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-03');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-04');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-05');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-06');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (3, '2023-05-07');
        END IF;
        IF NOT EXISTS (SELECT * FROM staff_working_mode WHERE staff_id = 4) THEN
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-04-30');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-01');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-07');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-08');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-14');
            INSERT INTO staff_working_mode(staff_id, working_day) VALUES (4, '2023-05-15');
        END IF;
    --INSERT log book services
        IF NOT EXISTS (SELECT * FROM log_book_services WHERE (staff_id = 1 OR staff_id = 2) AND start_service = '2023-05-01 09:00:00.000000') THEN
            INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (1, 5, '2023-05-01 10:00:00.000000', '2023-05-01 12:00:00.000000');
            INSERT INTO log_book_services(staff_id, user_id, start_service, finish_service) VALUES (2, 4, '2023-05-01 10:00:00.000000', '2023-05-01 11:00:00.000000');
        END IF;
    END
$do$




