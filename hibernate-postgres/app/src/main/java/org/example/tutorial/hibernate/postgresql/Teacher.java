package org.example.tutorial.hibernate.postgresql;

import org.checkerframework.checker.units.qual.s;
import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity

public class Teacher implements Serializable  {
 @GenericGenerator(name = "generator", strategy = "increment")
 private static final long serialVersionUID = 1L;
 @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
 @Column(name = "id")
 private Integer id;
// @GeneratedValue(generator = "generator")
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="salary")
    private Integer salary;

    

    //getters setters
public void setId(Integer id){
    this.id=id;
}
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setSalary(Integer salary){
        this.salary=salary;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Integer getSalary(){
        return salary;
    }
    public Integer getId(){
        return id;
    }
}
