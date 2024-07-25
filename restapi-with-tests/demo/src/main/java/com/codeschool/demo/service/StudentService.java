package com.codeschool.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.StudentRepo;
import com.codeschool.demo.repo.SubjectsRepo;



@Service
public class StudentService {
    private StudentRepo studentRepo;
    private SubjectsRepo subjectsRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo, SubjectsRepo subjectsRepo) {
        this.studentRepo = studentRepo;
        this.subjectsRepo = subjectsRepo;
    }

    //dependency injection
  

    public Student addStudent(Student student){
       return studentRepo.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Integer id){
        return studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public void deleteAllStudents(){
         studentRepo.deleteAll();
    }

    public void deleteStudentById(Integer id){
        studentRepo.deleteById(id);
    }

    public Student updateStudent(Integer id, Student tobeUpdated){
        Student updatedStudent = studentRepo.getById(id);
        updatedStudent.setFirstName(tobeUpdated.getFirstName());
        updatedStudent.setLastName(tobeUpdated.getLastName());
        updatedStudent.setAge(tobeUpdated.getAge());
        return studentRepo.save(updatedStudent);
    }

    public Student assignSubjectToStudent(Integer student_id, Integer subject_id) {
        Student student = studentRepo.findById(student_id)
                                     .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + student_id));
    
        Subject subject = subjectsRepo.findById(subject_id)
                                      .orElseThrow(() -> new NoSuchElementException("Subject not found with id: " + subject_id));
    
        Set<Subject> subjectSet = student.getSubjects();
        subjectSet.add(subject);
        student.setSubjects(subjectSet);
    
        return studentRepo.save(student);
    }
    
}
