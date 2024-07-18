package com.baps.kms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baps.kms.demo.entity.department;

public interface DepartmentsRepository extends JpaRepository<department,Integer> {

    
} 
