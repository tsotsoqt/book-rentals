-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS book_rental_app;

-- Use the database
USE book_rental_app;

-- Create the books table
CREATE TABLE BOOKS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn INT NOT NULL,
    pageCount INT NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

-- Create the users table
CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    onoma VARCHAR(20) NOT NULL,
    epitheto VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(50) NOT NULL
);

-- Create the rentals table
CREATE TABLE RENTALS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE
);