package com.jaqueMate.application.service.categories;

import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.model.Categories;
import com.jaqueMate.domain.port.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new InvalidDataException("Invalid category name");
        }

        Categories category = new Categories(categoryName);

        categoryRepository.save(category);

    }

}
