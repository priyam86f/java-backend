package org.example.tutorial.hibernate.postgresql;

import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STUDENT")
public class Student implements Serializable{
private static final long serialVersionUID = 1L;
@GenericGenerator(name = "generator", strategy = "increment")
@Id
@GeneratedValue(generator = "generator")

@Column(name = "ID")
private Integer id;

@Column(name = "TXT_FIRSTNAME")
private String firstName;

@Column(name = "TXT_LASTNAME")
private String lastName;

@Column(name = "NUM_ROLL")
private Integer roll;

@Column(name = "TXT_STANDARD")
private String standard;

public Integer getId() {
return id;
}
public void setId(Integer id) {
this.id = id;
}
public String getFirstName() {
return firstName;
}
public void setFirstName(String firstName) {
this.firstName = firstName;
}
public String getLastName() {
return lastName;
}
public void setLastName(String lastName) {
this.lastName = lastName;
}
public Integer getRoll() {
return roll;
}
public void setRoll(Integer roll) {
this.roll = roll;
}
public String getStandard() {
return standard;
}
public void setStandard(String standard) {
this.standard = standard;
}
}
