package com.company.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class myListened implements MessageListener {
    public void onMessage(Message m) {
        try{
            System.out.println("Сообщение получено из топика: " +
                    m.getBody(String.class));

        } catch (JMSException e){
            System.err.println("JMSException: " + e.toString());
        }
    }
}