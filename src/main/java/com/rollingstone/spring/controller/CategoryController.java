package com.rollingstone.spring.controller;

import com.rollingstone.events.CategoryEvent;
import com.rollingstone.exceptions.HTTP404Exception;
import com.rollingstone.spring.model.Category;
import com.rollingstone.spring.service.CategoryService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryController
		extends AbstractController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
	this.categoryService = categoryService;
    }

    /*---Add new Category---*/
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
	Category savedCategory = categoryService.save(category);
	CategoryEvent categoryCreatedEvent = new CategoryEvent("One Category is created", category);
	eventPublisher.publishEvent(categoryCreatedEvent);
	return ResponseEntity.ok().body("New Category has been saved with ID:" + savedCategory.getId());
    }

    /*---Get a Category by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") long id) {
	Optional<Category> returnedCategory = categoryService.get(id);
	Category category = returnedCategory.orElseThrow(() -> new HTTP404Exception("Resource Not Found"));

	CategoryEvent categoryCreatedEvent = new CategoryEvent("One Category is retrieved", category);
	eventPublisher.publishEvent(categoryCreatedEvent);
	return ResponseEntity.ok().body(category);
    }

    /*---get all Category---*/
    @GetMapping
    public @ResponseBody
    Page<Category> getCategorysByPage(@RequestParam(value = "pagenumber", defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
				    @RequestParam(value = "pagesize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
	return categoryService.getCategoriesByPage(pageNumber, pageSize);
    }

    /*---Update a Category by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
	checkResourceFound(this.categoryService.get(id));
	categoryService.update(id, category);
	return ResponseEntity.ok().body("Category has been updated successfully.");
    }

    /*---Delete a Category by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
	checkResourceFound(this.categoryService.get(id));
	categoryService.delete(id);
	return ResponseEntity.ok().body("Category has been deleted successfully.");
    }
}