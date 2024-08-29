package com.graphqlapi.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.graphqlapi.demo.entity.Book;
public interface BookRepo extends JpaRepository<Book,Integer> {

    
} 