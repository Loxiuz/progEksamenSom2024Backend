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