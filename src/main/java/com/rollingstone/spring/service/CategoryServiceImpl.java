package com.rollingstone.spring.service;

import com.rollingstone.spring.dao.CategoryDaoRepository;
import com.rollingstone.spring.model.Category;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDaoRepository categoryDao;

    @Override
    public Category save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Optional<Category> get(long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Page<Category> getCategoriesByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryName").descending());
        return categoryDao.findAll(pageable);
    }

    @Override
    public void update(long id, Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(long id) {
        categoryDao.deleteById(id);
    }

}
