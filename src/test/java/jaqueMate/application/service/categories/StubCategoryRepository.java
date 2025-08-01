package jaqueMate.application.service.categories;

import com.jaqueMate.domain.model.Categories;
import com.jaqueMate.domain.port.CategoryRepository;

import java.util.*;

public class StubCategoryRepository implements CategoryRepository {
    private final Map<Integer, Categories> categories = new HashMap<>();

    @Override
    public void save(Categories category) {
        categories.put(category.getId(), category);
    }

    @Override
    public Optional<Categories> findById(int id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public List<Categories> findAll() {
        return new ArrayList<>(categories.values());
    }

}
