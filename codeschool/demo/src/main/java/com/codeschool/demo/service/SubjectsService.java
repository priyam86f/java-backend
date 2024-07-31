package com.codeschool.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.StudentRepo;
import com.codeschool.demo.repo.SubjectsRepo;
import java.util.*;

@Service
public class SubjectsService {
    
    private SubjectsRepo subjectsRepo;

 
    @Autowired
    public  SubjectsService(SubjectsRepo subjectsRepo,StudentService studentService){
        this.subjectsRepo=subjectsRepo;
        this.studentService=studentService; 
    }
    @Autowired
    private StudentRepo studentRepo;

    private StudentService studentService;

    @Autowired
    private SubjectSpecification subjectSpecification;

    public Subject addSubject(Subject subject) {
        if (subject.getStudent() != null && !subject.getStudent().isEmpty()) {
            Set<Student> StudentsSet = new HashSet<>();
            for (Student student : subject.getStudent()) {
                Student persistedStudent = studentRepo.save(student);
                StudentsSet.add(persistedStudent);
            }
            subject.setStudent(StudentsSet);
        }
        return subjectsRepo.save(subject);
    }
    
    public void deleteAllSubjects(){
         subjectsRepo.deleteAll();
    }

    public void deleteSubjectById(Integer id){
         subjectsRepo.deleteById(id);
    }

    public Subject getSubjectById(Integer id){
        return subjectsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public List<Subject> findSubjects(String query) {
            Specification<Subject> spec = subjectSpecification.rsql(query);
            return subjectsRepo.findAll(spec);
        }

   
}
