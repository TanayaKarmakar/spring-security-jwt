create database spring_sec_jwt;

create user jwt_user;

grant all privileges on spring_sec_jwt.* to jwt_user;