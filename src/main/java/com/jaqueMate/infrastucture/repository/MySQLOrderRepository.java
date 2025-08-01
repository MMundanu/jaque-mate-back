package com.jaqueMate.infrastucture.repository;

import com.jaqueMate.domain.model.OrderStatus;
import main.java.com.jaqueMate.domain.port.OrderRepository;

import javax.sql.DataSource;
import main.java.com.jaqueMate.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class MySQLOrderRepository implements OrderRepository{
    private final DataSource dataSource;
    public MySQLOrderRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders (id, order_date, client_id, order_status) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getId());
            stmt.setString(2, order.getOrderDate());
            stmt.setInt(3, order.getClient_id());
            stmt.setString(4, order.getOrder_status().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving order", e);
        }
    }

    @Override
    public void cancel(int order_id) {
        updateOrderStatus(order_id, "CANCELED");
    }

    @Override
    public void finish(int order_id) {
        updateOrderStatus(order_id, "FINISHED");
    }

    private void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET order_status = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating order status", e);
        }
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getString("order_date"),
                        rs.getInt("client_id"),
                        OrderStatus.valueOf(rs.getString("order_status"))
                );
                return Optional.of(order);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching order by ID", e);
        }
    }

}
