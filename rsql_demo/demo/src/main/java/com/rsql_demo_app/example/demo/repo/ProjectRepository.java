package com.rsql_demo_app.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rsql_demo_app.example.demo.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {

    
} 
