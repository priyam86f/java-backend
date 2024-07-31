package com.codeschool.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.service.SubjectsService;
import java.util.*;

@RestController
public class SubjectsController {
    
    private SubjectsService subjectsService;

    @Autowired
    public SubjectsController(SubjectsService subjectsService){
        this.subjectsService=subjectsService;
    }

    @PostMapping(value="/subject")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject addSubject(@RequestBody Subject subject){
        return subjectsService.addSubject(subject);
    }

     @GetMapping(value="/subject")
    public List<Subject> findProjects(@RequestParam(required = false) String query) {
        return subjectsService.findSubjects(query);
    }

    @GetMapping(value="/subject/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subject getSubjectById(@PathVariable Integer id){
        return subjectsService.getSubjectById(id);
    }

    @DeleteMapping(value="/subject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllSubjects(){
        subjectsService.deleteAllSubjects();
    }
    @DeleteMapping(value="/subject/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubjectById(@PathVariable Integer id){
        subjectsService.deleteSubjectById(id);
    }
}
