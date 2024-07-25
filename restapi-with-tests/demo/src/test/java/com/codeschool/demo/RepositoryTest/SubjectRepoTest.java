package com.codeschool.demo.RepositoryTest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.SubjectsRepo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class SubjectRepoTest {

    @Autowired
    SubjectsRepo subjectsRepo;

    @Autowired
    TestEntityManager entityManager;

    Subject subject1 = new Subject();
    Subject subject2 = new Subject();
    Subject subject3 = new Subject();

    @BeforeEach
    public void testSetup(){
       subject1.setId(1);
       subject1.setLanguage("Java");
       subjectsRepo.save(subject1);

       subject2.setId(2);
       subject2.setLanguage("C++");
       subjectsRepo.save(subject2);

       subject3.setId(3);
       subject3.setLanguage("Python");
    }

    @Test //getAll
    public void getAllSubjectsRepoSuccess(){
        List<Subject> all_subjects = subjectsRepo.findAll();

       assertThat(all_subjects.size()).isEqualTo(2);
    }

    @Test //getbyId
    public void getSubjectbyIdRepoSuccess(){
      Subject subject_id_1 = subjectsRepo.getById(1);

      assertThat(subject_id_1).isNotNull();
      assertEquals(subject_id_1.getId(), subject1.getId());
    }

    @Test //createSubject
    public void createSubjectRepoSuccess(){
        List<Subject> beforeList = subjectsRepo.findAll();
        //the before list size must be 2 here

        assertThat(beforeList.size()).isEqualTo(2);
        //now add a new subject and then test the size
        subjectsRepo.save(subject3);

        List<Subject> afterList = subjectsRepo.findAll();
        assertThat(afterList.size()).isEqualTo(3);
    }

    @Test
    public void deleteAllSubjectsRepoSuccess(){
        List<Subject> previousSubjectList = subjectsRepo.findAll();
        assertThat(previousSubjectList).hasSize(2);
        subjectsRepo.deleteAll();
        List<Subject> currentSubjectList = subjectsRepo.findAll();
        assertThat(currentSubjectList).hasSize(0);
    }

    @Test
    public void deleteSubjectById(){
        Subject subject_id1 = subjectsRepo.getById(1);
        assertThat(subject_id1).isNotNull();

        subjectsRepo.deleteById(1);

        assertThat(subjectsRepo.findById(1)).isEmpty();
    }

    
}
