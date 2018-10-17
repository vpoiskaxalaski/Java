package com.company.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class mySender {
    private static Logger log = Logger.getLogger(mySender.class.getName());
    public static void main(String[] args) {
        try {
//создать соединение
            InitialContext ctx = new InitialContext();
            TopicConnectionFactory f = (TopicConnectionFactory) ctx.lookup("connect");//конектимся в сервере
            TopicConnection con = f.createTopicConnection();
            con.start();
//создать topic session
            TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic t = (Topic) ctx.lookup("mycon");//чтобы найти имя топика на сервере глассфиш
//создать TopicPublisher object
            TopicPublisher publisher = ses.createPublisher(t);
//создать TextMessage object
            TextMessage msg = ses.createTextMessage();
//записать сообщение
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter Msg, end to terminate:");
                String s = b.readLine();
                if (s.equals("end"))
                    break;
                msg.setText(s);
//послать
                publisher.publish(msg);
                System.out.println("Message successfully sent.");
                log.info("msg "+ s);
            }
//закрыть
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}