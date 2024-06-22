package com.minitest_1.service.impl;

import com.minitest_1.model.Category;
import com.minitest_1.model.dto.CategoryBookCount;
import com.minitest_1.repository.CategoryRepository;
import com.minitest_1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryBookCount> getCategoryBookCounts() {
        return categoryRepository.getCategoryBookCounts();
    }
}
