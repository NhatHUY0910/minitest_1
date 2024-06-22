package com.minitest_1.controller;

import com.minitest_1.model.Book;
import com.minitest_1.model.Category;
import com.minitest_1.model.dto.CategoryBookCount;
import com.minitest_1.service.IBookService;
import com.minitest_1.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBookService bookService;

    @GetMapping
    public ModelAndView listCategories() {
        ModelAndView mav = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    @GetMapping("/book-counts")
    public ModelAndView listBookCounts() {
        List<CategoryBookCount> categoryBookCounts = categoryService.getCategoryBookCounts();
        ModelAndView mav = new ModelAndView("/category/book-counts");
        mav.addObject("categoryBookNumber", categoryBookCounts);
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createCategoryForm() {
        ModelAndView mav = new ModelAndView("/category/create");
        mav.addObject("category", new Category());
        return mav;
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create New Category Successfully!");
        return "redirect:/categories";
    }

//    @GetMapping("/view-category/{id}")
//    public ModelAndView viewCategory(@PathVariable("id") Long id) {
//        Optional<Category> categoryOptional = categoryService.findById(id);
//        if (categoryOptional.isPresent()) {
//            Iterable<Book> bookIterable = bookService.findAllByCategory(categoryOptional.get());
//
//            ModelAndView mav = new ModelAndView("/book/list");
//            mav.addObject("books", bookIterable);
//            return mav;
//        }
//        return new ModelAndView("redirect:/categories");
//    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
