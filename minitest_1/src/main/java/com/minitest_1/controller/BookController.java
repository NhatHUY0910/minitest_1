package com.minitest_1.controller;

import com.minitest_1.model.Book;
import com.minitest_1.model.Category;
import com.minitest_1.service.IBookService;
import com.minitest_1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listBook(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books;

        if (search != null && !search.isEmpty()) {
            books = bookService.findAllByNameContaining(search, pageable);
        } else {
            books = bookService.findAll(pageable);
        }

        ModelAndView mav = new ModelAndView("/book/list");
        mav.addObject("books", books);
        mav.addObject("search", search);
        return mav;
    }

//    @GetMapping("/page")
//    public ModelAndView listBookPaginated(@RequestParam(defaultValue = "0") int page,
//                                          @RequestParam(defaultValue = "3") int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Book> books = bookService.findAll(pageable);
//        ModelAndView mav = new ModelAndView("/book/list");
//        mav.addObject("books", books);
//        return mav;
//    }
//
//    @GetMapping("/search")
//    public ModelAndView searchBook(@RequestParam("search") Optional<String> search, Pageable pageable){
//        Page<Book> books;
//        if(search.isPresent()){
//            books = bookService.findAllByNameContaining(search.get(), pageable);
//        } else {
//            books = bookService.findAll(pageable);
//        }
//
//        ModelAndView mav = new ModelAndView("/book/list");
//        mav.addObject("books", books);
//        return mav;
//    }

    @GetMapping("/create")
    public ModelAndView createBookForm(){
        ModelAndView mav = new ModelAndView("/book/create");
        mav.addObject("book", new Book());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/book/create");
        }

        bookService.save(book);
        ModelAndView mav = new ModelAndView("redirect:/books");
        redirectAttributes.addFlashAttribute("message", "Create New Book Successfully");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateBookForm(@PathVariable("id") Long id){
        Optional<Book> book = bookService.findById(id);
        if(book.isPresent()){
            ModelAndView mav = new ModelAndView("/book/update");
            mav.addObject("book", book.get());
            return mav;
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
           return new ModelAndView("/book/update");
        }

        bookService.save(book);
        ModelAndView mav = new ModelAndView("redirect:/books");
        redirectAttributes.addFlashAttribute("message", "Update Book Successfully");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Delete Book Successfully");
        return "redirect:/books";
    }
}
