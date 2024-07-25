package com.codeschool.demo.controller;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // cn d fgjbckskvnjrbgkc ajgz kv dnx gfkmd kfknc fgp sognfksnfia;VDKKGENFLET ME
    // NFJRB AFKIE V SCSKFBEOP FJR S

    @PostMapping(value = "/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping(value = "/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(value = "/student")
     @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

    @DeleteMapping(value = "/student/id")
    public void deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
    }

    @PatchMapping(value = "/student/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable Integer id, @RequestBody Map<String, Object> updatedStudent) {
        Student existingStudent = studentService.getStudentById(id);

        if (updatedStudent.containsKey("age")) {
            existingStudent.setAge((Integer) updatedStudent.get("age"));
        }
        if (updatedStudent.containsKey("first_name")) {
            existingStudent.setFirstName((String) updatedStudent.get("first_name"));
        }
        if (updatedStudent.containsKey("age")) {
            existingStudent.setLastName((String) updatedStudent.get("last_name"));
        }

        return studentService.updateStudent(id, existingStudent);
    }

    @PutMapping(value = "/student/{student_id}/{subject_id}")
    @ResponseStatus(HttpStatus.OK)
    public Student assignSubjectStudent(@PathVariable Integer student_id,
            @PathVariable Integer subject_id) {
        return studentService.assignSubjectToStudent(student_id, subject_id);
    }

    

}
