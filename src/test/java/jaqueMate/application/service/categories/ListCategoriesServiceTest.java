package jaqueMate.application.service.categories;

import com.jaqueMate.application.service.categories.ListCategoriesService;
import com.jaqueMate.domain.model.Categories;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ListCategoriesServiceTest {
    @Test
    public void listCategories(){
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        ListCategoriesService service = new ListCategoriesService(categoryRepository);
        Categories category1 = new Categories("Category 1");
        Categories category2 = new Categories("Category 2");
        Categories category3 = new Categories("Category 3");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        List<Categories> categories = service.execute();

        assertEquals(3, categories.size());

        assertTrue(categories.contains(category1));
        assertTrue(categories.contains(category2));
        assertTrue(categories.contains(category3));

        assertIterableEquals(List.of(category1, category2, category3), categories);



    }
}
