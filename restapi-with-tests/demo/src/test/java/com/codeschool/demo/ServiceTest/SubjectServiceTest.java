package com.codeschool.demo.ServiceTest;

import org.assertj.core.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.codeschool.demo.entity.Student;
import java.util.Optional;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.SubjectsRepo;
import com.codeschool.demo.service.SubjectsService;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    
    @Autowired
    MockMvc mockMvc;

    @Mock
    SubjectsRepo subjectsRepo;

    @InjectMocks
    SubjectsService subjectsService;
    Subject subject1 = new Subject();
    @BeforeEach
    public void setup(){
        
        subject1.setId(1);
        subject1.setLanguage("Java");
    }

    @Test //get all subjects test
    public void getAllSubjectsServiceTestSuccess(){
        List<Subject> all_subs = new ArrayList();
        all_subs.add(subject1);
        Mockito.when(subjectsRepo.findAll()).thenReturn(all_subs);

        List<Subject> subjects_list=subjectsService.getAllSubjects();

        assertNotNull(subjects_list);
        assertEquals(1, subjects_list.size());
    }

    @Test
    public void getSubjectByIdServiceTest(){
        Mockito.when(subjectsRepo.findById(1)).thenReturn(Optional.of(subject1));

        Subject subject_withid = subjectsService.getSubjectById(1);

        assertEquals(subject_withid.getId(), subject1.getId());
        assertEquals(subject_withid.getLanguage(), subject1.getLanguage());
    }

    @Test //create subject test
    public void createSubjectTestServiceSuccess(){
        Mockito.when(subjectsRepo.save(subject1)).thenReturn(subject1);

        Subject saved_subject = subjectsService.addSubject(subject1);

        assertEquals(saved_subject.getId(), subject1.getId());
        assertEquals(saved_subject.getLanguage(), subject1.getLanguage());
    }

    @Test //deleteAll
    public void deleteAllSubjectsServiceTest(){
        Mockito.doNothing().when(subjectsRepo).deleteAll();

        subjectsService.deleteAllSubjects();
    }

    @Test
    public void deleteSubjectByIdSuccess(){
        Mockito.doNothing().when(subjectsRepo).deleteById(1);

        subjectsService.deleteSubjectById(1);
    }
}
