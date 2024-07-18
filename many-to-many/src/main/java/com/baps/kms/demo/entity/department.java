package com.baps.kms.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "dept")
public class department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer deptId;

    @Column(name = "Region")
    private String region;

    @Column(name = "Role")
    private String role;

    @Column(name = "Since")
    private String since;

   @JsonIgnore
    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
  
    private Set<Karyakars> karyakars;

    // Getters and setters
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public Set<Karyakars> getKaryakars() {
        return this.karyakars != null ? this.karyakars : Collections.emptySet();
    }
    

    public void setKaryakars(Set<Karyakars> karyakars) {
        this.karyakars = karyakars;
    }
}
