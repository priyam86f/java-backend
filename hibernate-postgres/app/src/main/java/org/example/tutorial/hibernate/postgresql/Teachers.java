package org.example.tutorial.hibernate.postgresql;
import org.checkerframework.checker.units.qual.s;


import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Teachers2")
public class Teachers implements Serializable {
    private static final long serialVersionUID = 1L;
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name="ID")
    private Integer teacher_ID;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="salary")
    private Integer salary;

    

    //getters setters
public void setId(Integer id){
    this.teacher_ID=teacher_ID;
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
        return teacher_ID;
    }
}
