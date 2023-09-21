alter session set container = xepdb1;

-- CREATE ADMIN USER FOR TEST STRUCTURES INITIALIZATION
CREATE USER adminuser IDENTIFIED BY adminpass
  DEFAULT TABLESPACE users
  QUOTA 100M ON users;

GRANT DBA TO adminuser;