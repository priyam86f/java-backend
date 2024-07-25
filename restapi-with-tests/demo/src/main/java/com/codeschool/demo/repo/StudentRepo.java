package com.codeschool.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeschool.demo.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {

    
} 