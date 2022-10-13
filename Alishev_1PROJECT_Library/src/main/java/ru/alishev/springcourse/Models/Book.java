package ru.alishev.springcourse.Models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id_book;
    private int id_person;
    @NotEmpty(message = "Name of book can`t  be empty!")
    @Size(min = 2, max = 30, message = "Name of book should be between 2 and 30 characters!")
    private String name_book;
    @NotEmpty(message = "Name of author can`t be empty!")
    @Size(min = 2,max = 50, message = "Name of author should be between 2 and 50 characters!")
    private String author_book;
    @Min(value = 1001, message = "Age release book should be more than 1000 years!")
    @Max(value = 2022, message = "Age release book should`n be more than 2022 years!")
    private int year_release;

    public Book() {
    }

    public Book(int id_person, String name_book, String author_book, int year_release) {
        this.id_person = id_person;
        this.name_book = name_book;
        this.author_book = author_book;
        this.year_release = year_release;
    }

    public Book(String name_book, String author_book, int year_release) {
        this.name_book = name_book;
        this.author_book = author_book;
        this.year_release = year_release;
    }



    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public String getAuthor_book() {
        return author_book;
    }

    public void setAuthor_book(String author_book) {
        this.author_book = author_book;
    }

    public int getYear_release() {
        return year_release;
    }

    public void setYear_release(int year_release) {
        this.year_release = year_release;
    }
}
