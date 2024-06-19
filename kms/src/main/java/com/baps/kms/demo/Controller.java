package com.baps.kms.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Karyakars> getKaryakarById(@PathVariable Integer id){
        Karyakars karyakar = serviceClass.getKaryakarsById(id);
        if(serviceClass.getKaryakarsById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
        return ResponseEntity.ok(karyakar);
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

    

}
