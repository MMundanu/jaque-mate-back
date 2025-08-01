package com.jaqueMate.infrastucture.repository;

import com.jaqueMate.domain.model.Categories;
import com.jaqueMate.domain.port.CategoryRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySQLCategoryRepository implements CategoryRepository {
        private final DataSource dataSource;

        public MySQLCategoryRepository(DataSource dataSource) {
            this.dataSource = dataSource;
        }

    @Override
    public void save(Categories category) {
        String sql = "INSERT INTO categories (id, name) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving category", e);
        }
    }

    @Override
    public Optional<Categories> findById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categories category = new Categories(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                return Optional.of(category);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching category by ID", e);
        }
    }

    @Override
    public List<Categories> findAll() {
        String sql = "SELECT * FROM categories";
        List<Categories> results = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categories category = new Categories(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                results.add(category);
            }
            return results;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all categories", e);
        }
    }

}
