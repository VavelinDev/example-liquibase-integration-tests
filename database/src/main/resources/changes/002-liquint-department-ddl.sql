CREATE TABLE department (
                            id INT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            manager_id INT,
                            location VARCHAR(255)
);
GRANT SELECT, DELETE, INSERT, UPDATE ON department TO @database_user@;