package com.minitest_1.service.impl;

import com.minitest_1.model.Book;
import com.minitest_1.model.Category;
import com.minitest_1.repository.BookRepository;
import com.minitest_1.repository.CategoryRepository;
import com.minitest_1.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Iterable<Book> findAllByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining(String name, Pageable pageable) {
        return bookRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
