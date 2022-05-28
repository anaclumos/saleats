DROP DATABASE IF EXISTS saleats;

CREATE DATABASE saleats;

USE saleats;

DROP TABLE IF EXISTS Restaurant_details;

CREATE TABLE Restaurant_details (
  details_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  image_url VARCHAR(512) NOT NULL,
  address VARCHAR(512) NOT NULL,
  phone_no VARCHAR(512) NOT NULL,
  estimated_price VARCHAR(512),
  yelp_url VARCHAR(512) NOT NULL
);

DROP TABLE IF EXISTS Rating_details;

CREATE TABLE Rating_details (
  rating_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  review_count INTEGER NOT NULL,
  rating DOUBLE NOT NULL
);

DROP TABLE IF EXISTS Restaurant;

CREATE TABLE Restaurant (
  restaurant_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  restaurant_name VARCHAR(50) NOT NULL,
  restaurant_uid VARCHAR(512) NOT NULL,
  details_id INTEGER NOT NULL,
  rating_id INTEGER NOT NULL,
  FOREIGN KEY (details_id) REFERENCES Restaurant_details(details_id),
  FOREIGN KEY (rating_id) REFERENCES Rating_details(rating_id)
);

DROP TABLE IF EXISTS Category;

CREATE TABLE Category (
  category_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  category_name VARCHAR(50) NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES Restaurant(restaurant_id)
);

DROP TABLE IF EXISTS User;

CREATE TABLE User(
  user_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(50) NOT NULL,
  user_email VARCHAR(50) NOT NULL,
  user_password VARCHAR(500) NOT NULL
);