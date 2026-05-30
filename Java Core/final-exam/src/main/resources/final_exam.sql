-- Tạo database
CREATE DATABASE final_exam;
USE final_exam;

-- Tạo bảng user
CREATE TABLE users
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(100)               NOT NULL,
    email       VARCHAR(100)               NOT NULL UNIQUE,
    password    VARCHAR(255)               NOT NULL,
    exp_in_year INT DEFAULT 0,
    pro_skill   VARCHAR(255),
    role        ENUM ('EMPLOYEE', 'ADMIN') NOT NULL
);


-- Dữ liệu mẫu
INSERT INTO users (full_name, email, password, exp_in_year, pro_skill, role)
VALUES ('Nguyen Van A', 'nguyenvana@gmail.com', '123456', 2, 'Java, Spring Boot', 'EMPLOYEE'),
       ('Tran Thi B',
        'tranthib@gmail.com',
        '123456',
        5,
        'ReactJS, TypeScript',
        'EMPLOYEE'),
       ('Le Van C',
        'levanc@gmail.com',
        '123456',
        8,
        'Java, Microservices, Docker',
        'ADMIN'),
       ('Pham Thi D',
        'phamthid@gmail.com',
        '123456',
        3,
        'NodeJS, MongoDB',
        'EMPLOYEE'),
       ('Hoang Van E',
        'hoangvane@gmail.com',
        '123456',
        10,
        'System Design, AWS',
        'ADMIN');
