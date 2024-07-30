package com.rsql_demo_app.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.rsql_demo_app.example.demo.entity.Project;
import com.rsql_demo_app.example.demo.repo.ProjectRepository;
import com.rsql_demo_app.example.demo.service.ProjectSpecification;
import java.util.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectSpecification projectSpecification;

    @GetMapping
    public List<Project> findProjects(@RequestParam(required = false) String query
                                      ) {
        return projectRepository.findAll(projectSpecification.rsql(query));
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectRepository.getById(id);
    }

    

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}

