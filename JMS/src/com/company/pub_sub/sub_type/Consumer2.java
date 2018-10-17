package com.company.pub_sub.sub_type;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

import static javax.jms.JMSContext.CLIENT_ACKNOWLEDGE;

public class Consumer2 {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();

    Consumer2() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");

            //долговременная
            Destination priceDTopic = context.createTopic("PriceInfo");
            context.setClientID("qyh23w5");

            JMSConsumer consumer = context.createDurableConsumer((Topic)priceDTopic, "SecurityCenter");

            Message message = consumer.receive();

            System.out.println("Consumer2 got the text message: "+
                    message.getBody(String.class));

        }  catch (JMSException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public static  void main (String[] argvs){
        new Consumer2();
    }

}
