package com.baps.kms.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.baps.kms.demo.entity.Karyakars;

@Repository
public interface KaryakarsRepository extends JpaRepository<Karyakars,Integer> {
//Karyakars is the entity type that the repository will manage.
//Integer is the type of data type the primary key will use.

@Query(value = "SELECT * FROM karyakars LEFT JOIN karyakar_dept ON karyakars.id = karyakar_dept.karyakar_id", nativeQuery = true)
    List<Karyakars> findAllWithDepartments();
    
} 
