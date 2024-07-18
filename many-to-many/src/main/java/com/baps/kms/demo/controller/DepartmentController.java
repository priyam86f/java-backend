package com.baps.kms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baps.kms.demo.entity.Karyakars;
import com.baps.kms.demo.entity.department;

import com.baps.kms.demo.service.DepartmentService;
import java.util.*;

@RestController
public class DepartmentController {
   
    private DepartmentService departMentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departMentService=departmentService;
       
    }

    @GetMapping(value="/department")
    public List<department> getAllDepartments(){
        return departMentService.getAllDepartments();
    }

    @GetMapping(value="/department/{id}")
    public department getDepartmentById(@PathVariable Integer id){
        department Department = departMentService.getDepartmentById(id);
        if(departMentService.getDepartmentById(id)==null){
            throw new IllegalArgumentException("404 not found");
        }else{
        return departMentService.getDepartmentById(id);
        }
    }

    @PostMapping(value="/department")
    @ResponseStatus(HttpStatus.CREATED)
    public department createDepartment(@RequestBody department Department) {
        return departMentService.createDepartment(Department);
    }

    @DeleteMapping(value="/department")
    public void deleteAllDepartment(){
        departMentService.deleteAllDepartment();
    }

    @DeleteMapping(value="/department/{id}")
    public void deleteDepartmentById(@PathVariable Integer id){
        departMentService.getDepartmentById(id);
    }

}
