package com.minitest_1.service;

import com.minitest_1.model.Category;
import com.minitest_1.model.dto.CategoryBookCount;

import java.util.List;

public interface ICategoryService extends IGenericService<Category> {

    List<CategoryBookCount> getCategoryBookCounts();
}
