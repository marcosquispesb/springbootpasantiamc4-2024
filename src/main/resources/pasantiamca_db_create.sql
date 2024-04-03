CREATE USER usertest WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION;
    --Cambiar contrasenia del usuario
    ALTER USER usertest WITH PASSWORD '123456';
    --crear una base de datos con propietario especifico
    CREATE DATABASE springboot_pasantiamc4_2024 WITH OWNER usertest;
    --Asignar privilegios al usuario de de la base de datos
    GRANT ALL PRIVILEGES ON DATABASE springboot_pasantiamc4_2024 TO usertest;
