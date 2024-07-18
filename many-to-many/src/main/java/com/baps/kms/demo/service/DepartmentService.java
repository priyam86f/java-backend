package com.baps.kms.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baps.kms.demo.entity.Karyakars;
import com.baps.kms.demo.entity.department;

import com.baps.kms.demo.repository.DepartmentsRepository;
import com.baps.kms.demo.repository.KaryakarsRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentsRepository departmentRepository;

    @Autowired
    private KaryakarsRepository karyakarsRepository;

    private ServiceClass serviceClass;

    @Autowired
    public DepartmentService(DepartmentsRepository departmentRepository,ServiceClass serviceClass){
        this.departmentRepository=departmentRepository;
        this.serviceClass=serviceClass;
    }

    public department createDepartment(department Department) {
        Set<Karyakars> karyakarsSet = new HashSet<>();
        for (Karyakars karyakar : Department.getKaryakars()) {
            Karyakars persistedKaryakar = karyakarsRepository.save(karyakar);
            karyakarsSet.add(persistedKaryakar);
        }
        Department.setKaryakars(karyakarsSet);
        return departmentRepository.save(Department);
    }

    public List<department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public department getDepartmentById(Integer id){
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + id));
    }

    public  void deleteAllDepartment(){
         departmentRepository.deleteAll();
    }

    public void deleteDepartmentById(Integer id){
        departmentRepository.deleteById(id);
    }

}
