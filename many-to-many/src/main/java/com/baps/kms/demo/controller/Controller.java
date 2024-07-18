package com.baps.kms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.baps.kms.demo.entity.Karyakars;
import com.baps.kms.demo.entity.department;
import com.baps.kms.demo.service.ServiceClass;

import java.util.*;

@RestController
public class Controller {
    private  ServiceClass serviceClass;

    @Autowired
    public Controller(ServiceClass serviceClass){
        this.serviceClass=serviceClass;
    }

    @GetMapping(value="/karyakars")
    public List<Karyakars> getAllKaryakars(){
        return serviceClass.getKaryakars();
    }

    @GetMapping(value="/karyakars/{id}")
    public Karyakars getKaryakarById(@PathVariable Integer id){
        Karyakars karyakar = serviceClass.getKaryakarsById(id);
        if(serviceClass.getKaryakarsById(id)==null){
            throw new IllegalArgumentException("404 not found");
        }else{
        return serviceClass.getKaryakarsById(id);
        }
    }

    @PostMapping(value="/karyakars")
    @ResponseStatus(HttpStatus.CREATED)
    public Karyakars createKaryakar(@RequestBody Karyakars karyakars) {
        return serviceClass.createKaryakar(karyakars);
    }

    @DeleteMapping(value="/karyakars")
    public void deleteAllKaryakars(){
        serviceClass.deleteAllKaryakars();
    }

    @DeleteMapping(value="/karyakars/{id}")
    public void deleteKaryakarById(@PathVariable Integer id){
        serviceClass.deleteKaryakar(id);
    }

    @PatchMapping(value="/karyakars/{id}")
    public Karyakars updateKarayakar(@PathVariable Integer id,@RequestBody Map<String,Object> updatedKaryakar){
        Karyakars existingKaryakar = serviceClass.getKaryakarsById(id);
        if (updatedKaryakar.containsKey("age")) {
            existingKaryakar.setAge((Integer) updatedKaryakar.get("age"));
        }
        if (updatedKaryakar.containsKey("firstName")) {
            existingKaryakar.setFirstName((String) updatedKaryakar.get("firstName"));
        }
        if (updatedKaryakar.containsKey("lastName")) {
            existingKaryakar.setLastName((String) updatedKaryakar.get("lastName"));
        }
        if (updatedKaryakar.containsKey("city")) {
            existingKaryakar.setCity((String) updatedKaryakar.get("city"));
        }
        if (updatedKaryakar.containsKey("gender")) {
            existingKaryakar.setGender((String) updatedKaryakar.get("gender"));
        }
        return serviceClass.updateKaryakar(id, existingKaryakar);
    }

    //assign department to karyakar.
    @PutMapping("/{karyakarId}/{departmentId}")
    public Karyakars assignDepartmentToKaryakar(
        @PathVariable Integer karyakarId,
        @PathVariable Integer departmentId
    ){
return serviceClass.assignDepartmentToKaryakar(karyakarId,departmentId); 
    }

    @GetMapping(value="/with/dept")
    public List<Karyakars> getAllInfo(){
        return serviceClass.findAllWithDepartments();
    }
     

}
