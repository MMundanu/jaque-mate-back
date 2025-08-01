package com.jaqueMate.infrastucture.repository;

import main.java.com.jaqueMate.domain.port.OrderProductRepository;

import javax.sql.DataSource;
import main.java.com.jaqueMate.domain.model.OrderProduct;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySQLOrderProductRepository implements OrderProductRepository{

    private final DataSource dataSource;

    public MySQLOrderProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void add(OrderProduct orderProduct) {
        String sql = "INSERT INTO order_products (id, order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderProduct.getId());
            stmt.setInt(2, orderProduct.getOrder_id());
            stmt.setInt(3, orderProduct.getProduct_id());
            stmt.setInt(4, orderProduct.getQuantity());
            stmt.setDouble(5, orderProduct.getUnit_price());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding OrderProduct", e);
        }
    }

    @Override
    public void update(OrderProduct orderProduct) {
        String sql = "UPDATE order_products SET order_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderProduct.getOrder_id());
            stmt.setInt(2, orderProduct.getProduct_id());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.setDouble(4, orderProduct.getUnit_price());
            stmt.setInt(5, orderProduct.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating OrderProduct", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM order_products WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting OrderProduct", e);
        }
    }

    @Override
    public Optional<OrderProduct> getOrderProductByOrderIdAndProductId(int orderId, int productId) {
        String sql = "SELECT * FROM order_products WHERE order_id = ? AND product_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                OrderProduct op = new OrderProduct(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
                return Optional.of(op);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching OrderProduct by orderId and productId", e);
        }
    }

    @Override
    public Optional<OrderProduct> getById(int id) {
        String sql = "SELECT * FROM order_products WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                OrderProduct op = new OrderProduct(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
                return Optional.of(op);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching OrderProduct by id", e);
        }
    }

    @Override
    public List<OrderProduct> getAllByOrderId(int orderId) {
        String sql = "SELECT * FROM order_products WHERE order_id = ?";
        List<OrderProduct> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderProduct op = new OrderProduct(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
                results.add(op);
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching OrderProducts by orderId", e);
        }
    }


}
