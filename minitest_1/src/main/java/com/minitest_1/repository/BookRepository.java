package com.minitest_1.repository;

import com.minitest_1.model.Book;

import com.minitest_1.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Iterable<Book> findAllByCategory(Category category);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByNameContaining(String name, Pageable pageable);
}
