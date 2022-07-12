package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.CategoryDTO;
import vn.edu.hust.samiestate.service.impl.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    private final CategoryService categoryService;

    public CategoryAPI(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.save(categoryDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@RequestBody long[] ids) {
        if (ids.length > 0) {
            categoryService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }
}
