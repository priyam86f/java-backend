package com.codeschool.demo.ServiceTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.StudentRepo;
import com.codeschool.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentService studentService;

    Student student1 = new Student();
    Student student2 = new Student();

    Subject subject1 = new Subject();

    @BeforeEach
    public void beforeEachSetup() {

        student1.setId(1);
        student1.setFirstName("Priyam");
        student1.setLastName("Mehta");
        student1.setAge(22);

        student2.setId(2);
        student2.setFirstName("Dev");
        student2.setLastName("Bhagadia");
        student2.setAge(22);

        subject1.setId(1);
        subject1.setLanguage("Java");

    }

    @Test // createStudent
    public void testCreateStudentSuccess() {

        // when we are creating a student, it first gets saved in the repository.
        // after that, when we perform addStudent() in the service layer, internally it
        // uses the repository
        // to add the student in the database. we need to mock this exact same
        // behaviour.

        Mockito.when(studentRepo.save(student1)).thenReturn(student1);

        Student savedStudent = studentService.addStudent(student1);

        assertNotNull(savedStudent);
        assertEquals(student1.getId(), savedStudent.getId());
    }

    @Test
    //getallstudents test
    public void getAllStudentsTest(){
        List<Student> student_list = new ArrayList();
        student_list.add(student1);
        Mockito.when(studentRepo.findAll()).thenReturn(student_list);

        List<Student> students_list = studentService.getAllStudents();

        assertNotNull(students_list);
        assertEquals(1, students_list.size());
    }

    @Test
    //get student by id
    public void getStudentById(){
        when(studentRepo.findById(1)).thenReturn(Optional.of(student1));

        Student student_1 = studentService.getStudentById(1);
        
        assertEquals(student_1.getId(), student1.getId());
        assertNotNull(student_1);
    }

    @Test //delete student
    public void deleteAllStudentSuccess(){
        Mockito.doNothing().when(studentRepo).deleteAll();
        List<Student> all_students = new ArrayList();
        all_students.add(student1);
        all_students.add(student2);

        studentService.deleteAllStudents();
    }

    @Test
    public void deleteStudentByIdTestSuccess(){
       Mockito.doNothing().when(studentRepo).deleteById(1);

       studentService.deleteStudentById(1);

    }

    @Test
    public void updateStudentControllerSuccess(){
         Mockito.when(studentRepo.getById(1)).thenReturn(student1);
         Mockito.when(studentRepo.save(student1)).thenReturn(student1);

         Student new_student = studentService.updateStudent(1, student2);

         assertNotNull(new_student);
         assertEquals("Dev",new_student.getFirstName());
         assertEquals("Bhagadia", new_student.getLastName());
         assertEquals(22,new_student.getAge());
    }



}
