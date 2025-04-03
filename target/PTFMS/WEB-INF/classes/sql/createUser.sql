-- create user
CREATE USER 'cst8288'@'localhost' IDENTIFIED BY 'cst8288';

-- grant
GRANT ALL PRIVILEGES ON ptfms.* TO 'cst8288'@'localhost';

-- flush
FLUSH PRIVILEGES;