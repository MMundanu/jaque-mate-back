package com.jaqueMate.api.controller;


import com.jaqueMate.application.service.categories.CreateCategoryService;
import com.jaqueMate.application.service.categories.ListCategoriesService;
import com.jaqueMate.domain.model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/catedories")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final ListCategoriesService listCategoriesService;

    @Autowired
    public CategoryController(CreateCategoryService createCategoryService, ListCategoriesService listCategoriesService) {
        this.createCategoryService = createCategoryService;
        this.listCategoriesService = listCategoriesService;
    }

    @PostMapping("/add")
    public void createCategory(@RequestBody String name) {
        createCategoryService.execute(name);
    }

    @GetMapping("/")
    public List<Categories> getAllCategories() {
        return listCategoriesService.execute();
    }

}
