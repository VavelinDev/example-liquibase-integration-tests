alter session set container = xepdb1;

-- CREATE APP USER
CREATE USER appuser IDENTIFIED BY demopass
  DEFAULT TABLESPACE users
  QUOTA 100M ON users;

GRANT CREATE SESSION TO appuser;
GRANT CREATE TABLE TO appuser;
GRANT DELETE ANY TABLE TO appuser;
GRANT UPDATE ANY TABLE TO appuser;
GRANT INSERT ANY TABLE TO appuser;
GRANT SELECT ANY TABLE TO appuser;
GRANT CREATE SEQUENCE TO appuser;
GRANT CREATE ANY INDEX TO appuser;
GRANT SELECT ANY DICTIONARY TO appuser;

-- CREATE ADMIN USER FOR TEST STRUCTURES INITIALIZATION
CREATE USER adminuser IDENTIFIED BY adminpass
  DEFAULT TABLESPACE users
  QUOTA 100M ON users;

GRANT DBA TO adminuser;