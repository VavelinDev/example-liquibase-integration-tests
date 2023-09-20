CREATE TABLE employee (
                           id INT PRIMARY KEY,
                           first_name VARCHAR2(50) NOT NULL,
                           last_name VARCHAR2(50) NOT NULL,
                           job_title VARCHAR2(100),
                           hire_date DATE NOT NULL,
                           salary NUMBER(10, 2) NOT NULL
);