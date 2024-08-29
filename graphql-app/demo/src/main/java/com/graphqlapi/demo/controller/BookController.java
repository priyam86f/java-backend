package com.graphqlapi.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import java.util.*;

import com.graphqlapi.demo.entity.Book;
import com.graphqlapi.demo.repo.BookRepo;


@Controller
public class BookController {
    
    private BookRepo bookRepo;

    @Autowired
    public BookController(BookRepo bookRepo){
        this.bookRepo=bookRepo;
    }

    @QueryMapping
    public List<Book> books(){
      return bookRepo.findAll();
    }

    @QueryMapping
    public List<Book> author(){
        return bookRepo.findAll();
    }

    @QueryMapping
    public Book bookById(@Argument Integer id) {
        return bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    @MutationMapping
public Book updateBook( @Argument int id, 
                        @Argument String author, 
                        @Argument String title) {
    Book book = bookRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
    book.setAuthor(author);
    book.setTitle(title);
    return bookRepo.save(book);
}

@MutationMapping
    public Book addBook(@Argument String author, 
                        @Argument String title) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        return bookRepo.save(book);
    }

    @MutationMapping
    public void deleteBook(@Argument int id){
       
        bookRepo.deleteById(id);
    }


}
