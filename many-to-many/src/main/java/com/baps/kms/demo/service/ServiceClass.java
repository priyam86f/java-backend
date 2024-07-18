package com.baps.kms.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baps.kms.demo.entity.Karyakars;
import com.baps.kms.demo.entity.department;
import com.baps.kms.demo.repository.DepartmentsRepository;
import com.baps.kms.demo.repository.KaryakarsRepository;

import java.util.*;



@Service

public class ServiceClass {
    @Autowired
    private KaryakarsRepository karyakarsRepository;
    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    public ServiceClass(KaryakarsRepository karyakarsRepository){
        this.karyakarsRepository=karyakarsRepository;
    }

    public Karyakars createKaryakar(Karyakars karyakars){
        return karyakarsRepository.save(karyakars);
    }

    public List<Karyakars> getKaryakars(){
        return karyakarsRepository.findAll();
    }

    public Karyakars getKaryakarsById(Integer id){
        return karyakarsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + id));
    }

    public void deleteKaryakar(Integer id){
         karyakarsRepository.deleteById(id);
    }
    
    public void deleteAllKaryakars(){
        karyakarsRepository.deleteAll();
    }
    
    public List<Karyakars> findAllWithDepartments() {
        return karyakarsRepository.findAllWithDepartments();
    }
    public Karyakars updateKaryakar(Integer id,Karyakars updatedKaryakar){
        Karyakars updated = karyakarsRepository.getById(id);
        updated.setFirstName(updatedKaryakar.getFirstName());
        updated.setLastName(updatedKaryakar.getLastName());
        updated.setAge(updatedKaryakar.getAge());
        updated.setCity(updatedKaryakar.getCity());
        return karyakarsRepository.save(updated);
    

    }

   public Karyakars  assignDepartmentToKaryakar(Integer karyakarId,Integer  departmentId){
    Set<department> DepartmentSet;
Karyakars karyakars = karyakarsRepository.findById(karyakarId).get();
department Department = departmentsRepository.findById(departmentId).get();
DepartmentSet = karyakars.getDepartments();
DepartmentSet.add(Department);
karyakars.setDepartments(DepartmentSet);
return karyakarsRepository.save(karyakars);

    }
}
