package com.codeschool.demo.ControllerTest;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;


import org.springframework.util.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.codeschool.demo.controller.StudentController;
import com.codeschool.demo.entity.Student;
import com.codeschool.demo.entity.Subject;
import com.codeschool.demo.repo.StudentRepo;
import com.codeschool.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
  
    ObjectMapper objectMapper = new ObjectMapper();
    

    // this will create a mock object of student service
    @Mock
    private StudentService studentService;

    @InjectMocks // this will inject the created mock in the student controller
    private StudentController studentController;

    public Student student1 = new Student();
    public Student student2 = new Student();

    @BeforeEach
    public void beforeEachTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        student1.setFirstName("Priyam");
        student1.setAge(22);
        student1.setId(1);

        student2.setFirstName("Priyam");
        student2.setAge(22);
        student2.setId(2);
    }

    @Test
public void getAllControllerSuccess() throws Exception {
    List<Student> student_test = new ArrayList<>(Arrays.asList(student1, student2));

    Mockito.when(studentService.findStudents(Mockito.anyString())).thenReturn(student_test);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .get("/student")
            .param("query", "")  // Assuming the endpoint expects a query parameter
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String responseBody = result.getResponse().getContentAsString();
    System.out.println(responseBody);

    // Now perform assertions based on the actual structure of the response
    mockMvc.perform(MockMvcRequestBuilders
            .get("/student")
            .param("query", "")  // Assuming the endpoint expects a query parameter
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2))); // Adjust this path as needed
}


    @Test //get by id unit test
    public void getByIdControllerSuccess()throws Exception{
        Mockito.when(studentService.getStudentById(1)).thenReturn(student1);

        mockMvc.perform(MockMvcRequestBuilders
        .get("/student/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()) // Assert status is 200 OK
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) 
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Priyam")) 
        .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(22)); 
    }

   @Test //post api unit test
public void createControllerSuccess() throws Exception {
    // Given: Mock the behavior of the service to save the student and return it
    Mockito.when(studentService.addStudent(Mockito.any(Student.class))).thenReturn(student1);

    // When & Then: Perform the POST request and assert the response
    mockMvc.perform(MockMvcRequestBuilders
            .post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(student1))) // Convert student1 to JSON string
            .andExpect(status().isCreated()) // Assert status is 201 Created
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) // Assert ID field
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Priyam")) // Assert first name field
            .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(22)); // Assert age field
}

@Test //delete api uni test
public void deleteControllerSuccess() throws Exception{
  Mockito.doNothing().when(studentService).deleteAllStudents();

  mockMvc.perform(MockMvcRequestBuilders
  .delete("/student")
  .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
}

@Test //patch api unit test
public void patchControllerSuccess() throws Exception {
   int student_id = 1;
   Student oldStudent = new Student();
   oldStudent.setId(student_id);
   oldStudent.setFirstName("Priyam");
   oldStudent.setLastName("Mehta");
   oldStudent.setAge(22);

   Student newStudent = new Student();
   newStudent.setFirstName("Dev");
   newStudent.setLastName("Bhagadia");
   newStudent.setAge(22);

   //mocking the updateStudent method
   Mockito.when(studentService.updateStudent(student_id,oldStudent)).thenReturn(newStudent);

   Mockito.when(studentService.getStudentById(student_id)).thenReturn(oldStudent);
 
   mockMvc.perform(MockMvcRequestBuilders.patch("/student/{id}",student_id)
   .contentType(MediaType.APPLICATION_JSON)
   .content(new ObjectMapper().writeValueAsString(oldStudent)))
   .andExpect(status().isOk())
   .andExpect(jsonPath("$.firstName").value(newStudent.getFirstName()))
   .andExpect(jsonPath("$.lastName").value(newStudent.getLastName()))
   .andExpect(jsonPath("$.age").value(newStudent.getAge()));



}

    @Test
public void assignSubjectStudent() throws Exception {
    int studentId = 1;
    int subjectId = 2;

    Student student = new Student();
    student.setId(studentId);
    student.setFirstName("John");
    student.setLastName("Doe");
    student.setAge(20);

    Subject subject = new Subject();
    subject.setId(subjectId);
    subject.setLanguage("Java");

    student.setSubjects(Set.of(subject));

    Mockito.when(studentService.assignSubjectToStudent(studentId, subjectId)).thenReturn(student);

    mockMvc.perform(MockMvcRequestBuilders.put("/student/{student_id}/{subject_id}",studentId,subjectId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20))
            .andExpect(MockMvcResultMatchers.jsonPath("$.subjects[0].id").value(subjectId))
            .andExpect(MockMvcResultMatchers.jsonPath("$.subjects[0].language").value("Java"));

    Mockito.verify(studentService, Mockito.times(1)).assignSubjectToStudent(studentId, subjectId);
}
    
}
