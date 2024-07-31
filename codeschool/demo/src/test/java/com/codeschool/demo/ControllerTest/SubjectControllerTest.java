package com.codeschool.demo.ControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.codeschool.demo.controller.SubjectsController;
import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.service.StudentService;
import com.codeschool.demo.service.SubjectsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    

    @Autowired
    private MockMvc mockMvc;

    @Mock
    SubjectsService subjectsService;

    @Mock
    StudentService studentService;



    @InjectMocks
    SubjectsController subjectController;

    Subject subject1 = new Subject();
    Subject subject2 = new Subject();

    @BeforeEach
    public void beforeEachTest(){
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();
        subject1.setId(1);
        subject1.setLanguage("Java");

        // subject2.setId(2);
        // subject2.setLanguage("C++");
    }

    @Test
public void getAllSubjectControllerTestSuccess() throws Exception {
    // Prepare test data
    List<Subject> subjectTestList = new ArrayList<>(Arrays.asList(subject1, subject2));
    
    // Mock the service call with a query
    String query = ""; // Use an empty query to get all subjects
    Mockito.when(subjectsService.findSubjects(query)).thenReturn(subjectTestList);

    // Execute the request and perform assertions
    mockMvc.perform(MockMvcRequestBuilders
            .get("/subject?query=" + query) // Append the query parameter
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
           // Check second subject's name
}


    @Test //interview question : you are asked to write a unit test on a get by id request
    public void getSubjectbyIdTestSuccess() throws Exception{
      Mockito.when(subjectsService.getSubjectById(1)).thenReturn(subject1);

      mockMvc.perform(MockMvcRequestBuilders
      .get("/subject/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("Java"));
    }

    @Test
    public void createSubjectSuccess() throws Exception {
       
    
        Subject subject_1 = new Subject();
        subject_1.setId(1);
        subject_1.setLanguage("Java");
      
        Mockito.when(subjectsService.addSubject(Mockito.any(Subject.class))).thenReturn(subject_1);
    
        mockMvc.perform(MockMvcRequestBuilders
            .post("/subject")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(subject_1)))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("Java"));
          
    }

    //delete subject test
    @Test
    public void deleteSubjectSuccess() throws Exception{
       Mockito.doNothing().when(subjectsService).deleteAllSubjects();

       mockMvc.perform(MockMvcRequestBuilders.delete("/subject" )
       .contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isNoContent());
    }

    //delete by id test
    @Test
    public void deleteSubjectById() throws Exception {
        Mockito.doNothing().when(subjectsService).deleteSubjectById(1);
    
        mockMvc.perform(MockMvcRequestBuilders.delete("/subject/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    
        Mockito.verify(subjectsService, Mockito.times(1)).deleteSubjectById(1);
    }



    
    
}

    

