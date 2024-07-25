package com.codeschool.demo.RepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.codeschool.demo.entity.Student;
import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;

import com.codeschool.demo.repo.StudentRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*ensures that Spring does not replace your configured datasource 
(e.g., PostgreSQL) with an in-memory database (like H2) for tests. */
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TestEntityManager entityManager;

    //methods to test : save,findAll,findById,deleteById,deleteAll
Student student_1 = new Student();
Student student_2 = new Student();
    @BeforeEach
    public void setUp(){
        student_1.setId(1);
        student_1.setFirstName("Dev");
        student_1.setLastName("Bhagadia");
        student_1.setAge(22);
        student_2.setId(2);
        student_2.setFirstName("Dev");
        student_2.setLastName("Bhagadia");
        student_2.setAge(22);
        studentRepo.save(student_1);
        studentRepo.save(student_2);

    }

    @Test
    public void saveMethodSuccess() {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("Priyam");
        student.setLastName("Mehta");
        student.setAge(22);

      

        // Save the student entity
        Student savedStudent = studentRepo.save(student);

        // Retrieve the saved entity
        Student foundStudent = studentRepo.findById(savedStudent.getId()).orElse(null);

        // Assertions to verify the saved entity
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getFirstName()).isEqualTo("Priyam");
        assertThat(foundStudent.getLastName()).isEqualTo("Mehta");
        assertThat(foundStudent.getAge()).isEqualTo(22);
    }

    @Test //test for get student by id
    public void getByIdRepositorySuccess(){
        Student student_1 = new Student();
        student_1.setId(1);
        Student foundStudent = studentRepo.getById(student_1.getId());

        assertThat(foundStudent.getId()).isEqualTo(student_1.getId());
    }

    @Test //get all students
    public void getAllStudents(){
        List<Student> student_list = studentRepo.findAll();

        assertThat(student_list).hasSize(2);
    }

    @Test //delete all
    public void deleteAllStudents(){
        List<Student> previousStudentList = studentRepo.findAll();
        assertThat(previousStudentList).hasSize(2);
        studentRepo.deleteAll();
        List<Student> currentStudentList = studentRepo.findAll();
        assertThat(currentStudentList).hasSize(0);
    }

    @Test //delete by id
    public void deleteStudentById(){
       studentRepo.deleteById(1);

       assertThat(studentRepo.findById(1)).isEmpty();
    }
    
}
