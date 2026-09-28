-- Optional SQL schema for extending this project to MySQL/JDBC.

CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    course VARCHAR(100) NOT NULL,
    marks DECIMAL(5,2) NOT NULL CHECK (marks BETWEEN 0 AND 100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO students (id, name, email, course, marks) VALUES
(101, 'Rahul Patil', 'rahul@example.com', 'Computer Engineering', 86.50),
(102, 'Sneha Sharma', 'sneha@example.com', 'Information Technology', 91.00);
