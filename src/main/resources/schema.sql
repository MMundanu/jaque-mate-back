CREATE DATABASE IF NOT EXISTS jaqueMate2_db;
USE jaqueMate2_db;

-- Tabla: users
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );

-- Tabla: categories
CREATE TABLE IF NOT EXISTS categories (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

-- Tabla: products
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
    );

-- Tabla: orders
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    date VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

-- Tabla: order_products
CREATE TABLE IF NOT EXISTS order_products (
     id INT PRIMARY KEY,
     order_id INT NOT NULL,
     product_id INT NOT NULL,
     quantity INT NOT NULL,
     unit_price DOUBLE NOT NULL,
     FOREIGN KEY (order_id) REFERENCES orders(id),
     FOREIGN KEY (product_id) REFERENCES products(id)
     );
