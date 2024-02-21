CREATE DATABASE lesson_booking_db;
CREATE USER 'lesson_booking_admin'@'localhost' IDENTIFIED BY 'pfc-mysql-admin';
GRANT ALL PRIVILEGES ON lesson_booking_db.* TO 'lesson_booking_admin'@'localhost';