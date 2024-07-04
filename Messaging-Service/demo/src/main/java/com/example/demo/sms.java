package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="sms_service")
public class sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="Destination")
    private String smsDestinationNumber;

    @Column(name="Message")
    private String messageToSend;

    public void setSmsDestinationNumber(String smsDestinationNumber){
        this.smsDestinationNumber=smsDestinationNumber;
    }

    public void setMessageToSend(String messageToSend){
        this.messageToSend=messageToSend;
    }
 

    public String getSmsDestinationNumber(){
        return smsDestinationNumber;
    }

    public String getMessageToSend(){
        return messageToSend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
