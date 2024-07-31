package com.codeschool.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.*;

@Entity
@Table(name="subject")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="subject_id")
    private Integer id;

    @Column(name="language")
    private String language;

@JsonIgnore  
    @ManyToMany(mappedBy = "subject",fetch = FetchType.EAGER)
    private Set<Student> student=new HashSet();

    public String getLanguage(){
        return language;
    }
    
    public Integer getId(){
        return id;
    }
    
    public Set<Student> getStudent(){
        return student;
    }

    public void setStudent(Set<Student> student){
        this.student=student;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public void setLanguage(String language){
        this.language=language;
    }


}
