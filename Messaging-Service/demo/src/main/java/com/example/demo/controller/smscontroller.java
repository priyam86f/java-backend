package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.demo.sms;
import com.example.demo.service.smsService;



@RestController
@RequestMapping(value="/sms")

public class smscontroller {

    @Autowired
    smsService SMSService;
    
    @PostMapping()
    public ResponseEntity<sms> SendSMS(@RequestBody sms smsreq) {
        sms createdSMS = SMSService.createSMS(smsreq);
        
        if (createdSMS != null) {
            // Assuming sendSms returns a status or throws an exception on failure
            String status = SMSService.sendSms(createdSMS.getSmsDestinationNumber(), createdSMS.getMessageToSend());
            
            if (status.equals("queued") || status.equals("sent")) {
                return new ResponseEntity<>(createdSMS, HttpStatus.OK);
            } else {
                // Handle failure to send SMS
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<sms> getAllSMS(){
        return SMSService.getMessages();
    }



    
    

}
