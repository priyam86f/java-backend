package com.baps.kms.demo;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="karyakars")
public class Karyakars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private Integer age;

    @Column(name="city")
    private String city;

    @Column(name="gender")
    private String gender;

    //getters and setters

    public void setId(Integer id){
        this.id=id;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public void setAge(Integer age){
        this.age=age;
    }

    public void setCity(String city){
        this.city=city;
    }

    public void setGender(String gender){
        this.gender=gender;
    }

    public Integer getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    public String getCity(){
        return city;
    }

    public Integer getAge(){
        return age;
    }
}
