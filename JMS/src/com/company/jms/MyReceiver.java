package com.company.jms;

import javax.jms.*;
import javax.naming.InitialContext;

public class MyReceiver {
    public static void main(String[] args) {
        try {
//создать и стартовать connection
            InitialContext ctx=new InitialContext();
            TopicConnectionFactory f=(TopicConnectionFactory)ctx.lookup("connect");
            TopicConnection con=f.createTopicConnection();
            con.start();
// создать topic session
            TopicSession ses=con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
// получить Topic object
            Topic t=(Topic)ctx.lookup("mycon");
//создать TopicSubscriber
            TopicSubscriber receiver=ses.createSubscriber(t);
            //ассинхронно
// создать listener object
            myListened listener= new myListened();
// регистрируем listener object приемником
            receiver.setMessageListener(listener);//чтобы получить сообщение
            System.out.println("Subscriber1 is ready, waiting for messages...");
            System.out.println("press Ctrl+c to shutdown...");
            while(true){
                Thread.sleep(1000);
            }
        }catch(Exception e){System.out.println(e);}
    }
}