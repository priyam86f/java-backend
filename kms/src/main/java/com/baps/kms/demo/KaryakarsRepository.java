package com.baps.kms.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryakarsRepository extends JpaRepository<Karyakars,Integer> {

    
} 
