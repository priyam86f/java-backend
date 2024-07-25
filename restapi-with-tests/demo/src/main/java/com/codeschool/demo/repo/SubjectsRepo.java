package com.codeschool.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeschool.demo.entity.Subject;

public interface SubjectsRepo extends JpaRepository<Subject,Integer> {

    
} 
