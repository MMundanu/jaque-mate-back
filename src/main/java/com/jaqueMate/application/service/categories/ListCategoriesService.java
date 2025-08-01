package com.jaqueMate.application.service.categories;

import com.jaqueMate.domain.model.Categories;
import com.jaqueMate.domain.port.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCategoriesService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public ListCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Categories> execute(){
        return categoryRepository.findAll();
    }

}
