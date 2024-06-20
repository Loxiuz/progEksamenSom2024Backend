INSERT INTO participant (full_name, email, gender, birthdate, club)
VALUES
    ('John Doe', 'john.doe@example.com', 'male', '1980-01-01', 'Club A'),
    ('Jane Smith', 'jane.smith@example.com', 'female', '1985-02-02', 'Club B'),
    ('Alice Johnson', 'alice.johnson@example.com', 'female', '1990-03-03', 'Club C'),
    ('Bob Williams', 'bob.williams@example.com', 'male', '1995-04-04', 'Club A');

INSERT INTO discipline (name, result_type)
VALUES
    ('100m', 'time'),
    ('Long jump', 'distance'),
    ('High jump', 'height'),
    ('Shot put', 'distance');

INSERT INTO result (participant_id, discipline_id, date, value)
VALUES
    (1, 1, '2022-01-01', '00:00:10.20'),
    (2, 2, '2022-01-02', '6.50'),
    (3, 3, '2022-01-03', '2.10'),
    (4, 4, '2022-01-04', '15.20'),
    (1, 2, '2022-01-05', '7.30'),
    (2, 3, '2022-01-06', '1.90'),
    (3, 4, '2022-01-07', '16.40'),
    (4, 1, '2022-01-08', '00:00:09.80');

INSERT INTO participant_discipline (participant_id, discipline_id)
VALUES (1, 1), (2, 2), (3, 3), (4, 4), (1, 2), (2, 3), (3, 4), (4, 1);
