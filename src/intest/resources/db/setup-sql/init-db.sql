alter session set container = xepdb1
/
CREATE USER @database_user@ IDENTIFIED BY demopass
    DEFAULT TABLESPACE users
    QUOTA 100M ON users
/

GRANT CREATE SESSION TO @database_user@
/
GRANT CREATE TABLE TO @database_user@
/
GRANT DELETE ANY TABLE TO @database_user@
/
GRANT UPDATE ANY TABLE TO @database_user@
/
GRANT INSERT ANY TABLE TO @database_user@
/
GRANT SELECT ANY TABLE TO @database_user@
/
GRANT CREATE SEQUENCE TO @database_user@
/
GRANT CREATE ANY INDEX TO @database_user@
/
GRANT SELECT ANY DICTIONARY TO @database_user@
/