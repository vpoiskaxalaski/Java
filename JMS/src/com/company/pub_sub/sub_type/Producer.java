package com.company.pub_sub.sub_type;

import com.sun.messaging.ConnectionConfiguration;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;

import static javax.jms.JMSContext.AUTO_ACKNOWLEDGE;
import static javax.jms.JMSContext.CLIENT_ACKNOWLEDGE;

public class Producer {
    public static  void  main(String[] argv) {
        com.sun.messaging.ConnectionFactory factory= new com.sun.messaging.ConnectionFactory();

        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

            JMSProducer producer = context.createProducer();

            //публикация топика
            Destination priceInfo  = context.createTopic("PriceInfo");

            producer.send(priceInfo,"Epam 100.22");

            System.out.println("Placed an information ");

        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
