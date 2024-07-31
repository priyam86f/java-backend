package com.codeschool.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.codeschool.demo.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

    
} 