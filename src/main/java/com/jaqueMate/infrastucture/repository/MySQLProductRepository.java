package com.jaqueMate.infrastucture.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import main.java.com.jaqueMate.domain.model.Product;
import main.java.com.jaqueMate.domain.port.ProductRepository;

@Repository
public class MySQLProductRepository implements ProductRepository {

    private final DataSource dataSource;

    @Autowired
    public MySQLProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (id, name, description, price, category_id, image, stock) VALUES (?, ?, ?, ?, ?, ?, ?,)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getCategoryId());
            stmt.setString(6, product.getImage());
            stmt.setInt(7, product.getStock());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving product", e);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name=?, description=?, price=?, category_id=?, image=?, stock=? WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getCategoryId());
            stmt.setString(5, product.getImage());
            stmt.setInt(6, product.getStock());
            stmt.setInt(7, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    @Override
    public void delete(Product product) {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }

    @Override
    public Optional<Product> getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getString("image"),
                        rs.getInt("stock")
                );
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product by ID", e);
        }
    }

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getString("image"),
                        rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all products", e);
        }
        return products;
    }

    @Override
    public void reduceStock(int productId, int stockToReduce) {
        String sql = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stockToReduce);
            stmt.setInt(2, productId);
            stmt.setInt(3, stockToReduce);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error reducing stock", e);
        }
    }

    @Override
    public void addStock(int productId, int stockToAdd) {
        String sql = "UPDATE products SET stock = stock + ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stockToAdd);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding stock", e);
        }
    }

}

