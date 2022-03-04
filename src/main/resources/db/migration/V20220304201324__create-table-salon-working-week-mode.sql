CREATE TABLE IF NOT EXISTS salons_working_week_mode
(
    id SERIAL PRIMARY KEY NOT NULL,
    salon_id INT NOT NULL,
    day_week VARCHAR(10) NOT NULL,
    start_working VARCHAR(5) NOT NULL,
    end_working VARCHAR(5) NOT NULL
);