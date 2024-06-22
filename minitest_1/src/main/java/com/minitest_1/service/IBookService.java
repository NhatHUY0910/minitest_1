package com.minitest_1.service;

import com.minitest_1.model.Book;
import com.minitest_1.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGenericService<Book> {

    Iterable<Book> findAllByCategory(Category category);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByNameContaining(String name, Pageable pageable);

}
