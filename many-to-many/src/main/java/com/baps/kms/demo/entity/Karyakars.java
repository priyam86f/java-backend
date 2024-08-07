package com.baps.kms.demo.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Karyakars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private String gender;

    // many to many bidirectional mapping.
    //this is the parent table.

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="karyakar_dept",
        joinColumns = @JoinColumn(name="karyakar_id"),
        inverseJoinColumns=  @JoinColumn(name="department_id")
        )

        
    private Set<department> departments;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Set<department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<department> departments) {
        this.departments = departments;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

}
