package com.codeschool.demo.ServiceTest;

import org.assertj.core.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.SubjectsRepo;
import com.codeschool.demo.service.SubjectSpecification;
import com.codeschool.demo.service.SubjectsService;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    
    @Autowired
    MockMvc mockMvc;

    @Mock
    SubjectsRepo subjectsRepo;

    @Mock
    SubjectSpecification subjectSpecification;

    @InjectMocks
    SubjectsService subjectsService;

    Subject subject1 = new Subject();
    @BeforeEach
    public void setup(){
        
        subject1.setId(1);
        subject1.setLanguage("Java");
    }

//  @Test
//     public void findAllSubjectsServiceTestSuccess() {
//         String query = ""; // Define a query input if needed, adjust as necessary
//         List<Subject> allSubs = new ArrayList<>();
//         allSubs.add(subject1);

//         // Mock the Specification
//         Specification<Subject> spec = mock(Specification.class);
//         when(subjectSpecification.rsql(query)).thenReturn(spec);

//         // Mock the repository call with the Specification
//         when(subjectsRepo.findAll(spec)).thenReturn(allSubs);

//         // Call the service method with the query parameter
//         List<Subject> subjectsList = subjectsService.findSubjects(query);

//         // Assertions
//         assertNotNull(subjectsList);
//         assertEquals(1, subjectsList.size());
//         assertEquals(subject1, subjectsList.get(0)); // Check that the returned subject is the same as subject1
//     }

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
