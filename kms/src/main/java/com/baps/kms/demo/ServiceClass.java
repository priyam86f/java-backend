package com.baps.kms.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;



@Service

public class ServiceClass {
    private KaryakarsRepository karyakarsRepository;

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

    public Karyakars updateKaryakar(Integer id,Karyakars updatedKaryakar){
        Karyakars updated = karyakarsRepository.getById(id);
        updated.setFirstName(updatedKaryakar.getFirstName());
        updated.setLastName(updatedKaryakar.getLastName());
        updated.setAge(updatedKaryakar.getAge());
        updated.setCity(updatedKaryakar.getCity());
        return karyakarsRepository.save(updated);
    

    }
}
