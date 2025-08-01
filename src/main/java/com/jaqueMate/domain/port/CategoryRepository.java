package com.jaqueMate.domain.port;

import com.jaqueMate.domain.model.Categories;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository {
    void save(Categories category);
    Optional<Categories> findById(int id);
    List<Categories> findAll();
}
