package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.sms;
import com.example.demo.repository.repo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class smsService {

    private repo Repo;

    @Autowired
    public smsService(repo Repo){
        this.Repo=Repo;
    }

    @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO_OUTGOING}")
    String OUTGOING_NUMBER;

    @PostConstruct
    private void setup(){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    public String sendSms(String smsNumber, String smsMessage){
        Message message = Message.creator(
            new PhoneNumber(smsNumber),
             new PhoneNumber(OUTGOING_NUMBER),
             smsMessage).create();
        return message.getStatus().toString();
    }

    public List<sms> getMessages(){
        return Repo.findAll();
    }

    public sms getMessageById(@PathVariable Integer id){
        return Repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Message not found with id: " + id));
    }

    public void deleteAllSMS(){
        Repo.deleteAll();
    }

    public sms createSMS(sms SMS){
        return Repo.save(SMS);
    }

}
