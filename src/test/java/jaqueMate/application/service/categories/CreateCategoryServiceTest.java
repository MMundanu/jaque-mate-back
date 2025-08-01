package jaqueMate.application.service.categories;


import com.jaqueMate.application.service.categories.CreateCategoryService;
import com.jaqueMate.domain.exceptions.InvalidDataException;
import com.jaqueMate.domain.exceptions.NotFoundException;
import com.jaqueMate.domain.model.Categories;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class CreateCategoryServiceTest {
    @Test
    public void createCategory() {
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        CreateCategoryService service = new CreateCategoryService(categoryRepository);

        service.execute("Mate");

        Optional<Categories> category = categoryRepository.findById(1);
        assertTrue(category.isPresent());
        assertEquals("Mate", category.get().getName());


    }
    @Test
    public void shouldThrewExceptionWhenCategoryNameIsBlank() {
        StubCategoryRepository categoryRepository = new StubCategoryRepository();
        CreateCategoryService service = new CreateCategoryService(categoryRepository);

        InvalidDataException exception = assertThrows(
                InvalidDataException.class,
                () -> service.execute("  ")
        );
        assertEquals("Invalid category name", exception.getMessage());


    }

}
