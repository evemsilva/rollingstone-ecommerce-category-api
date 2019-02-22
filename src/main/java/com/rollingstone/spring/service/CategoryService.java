package com.rollingstone.spring.service;

import com.rollingstone.spring.model.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Category save(Category product);

    Optional<Category> get(long id);

    Page<Category> getCategoriesByPage(Integer pageNumber, Integer pageSize);

    void update(long id, Category product);

    void delete(long id);
}
