package com.minitest_1.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "tên sách không được để trống")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "tên sách không được chứa chữ số và kí tự đặc biệt")
    private String name;

    @NotBlank(message = "tên tác giả không được để trống")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "tên tác giả không được chứa chữ số và kí tự đặc biệt")
    @Size(max = 32, message = "tên tác giả chỉ được chứa tối đa 32 kí tự")
    private String author;

    @NotNull(message = "đơn giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "đơn giá phải lớn hơn 0")
    @DecimalMax(value = "999999.0", message = "đơn giá không thể lớn hơn 999.999")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @Nullable
    private String image;

    public Book() {
    }

    public Book(String name, String author, double price, Category category, String image) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Book(Long id, String name, String author, double price, Category category, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
