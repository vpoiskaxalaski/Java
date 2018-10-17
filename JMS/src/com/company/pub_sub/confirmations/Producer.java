package com.company.pub_sub.confirmations;

import com.sun.messaging.ConnectionConfiguration;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;

import static javax.jms.JMSContext.AUTO_ACKNOWLEDGE;
import static javax.jms.JMSContext.CLIENT_ACKNOWLEDGE;

public class Producer {
    public static  void  main(String[] argv) {
        //ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
        com.sun.messaging.ConnectionFactory factory= new com.sun.messaging.ConnectionFactory();

        //объекты JMS  соединения и сессии. Нужно соединение с MOM, обмен идет в сессии
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

            // администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("BankOrderDestination");

            // JMSProducer интерфейс с методом для посылки сообщения получателю
            JMSProducer producer1 = context.createProducer().setPriority(2);
            JMSProducer producer2 = context.createProducer().setPriority(9);

            for (int i =0 ; i<2;i++){
                //Send msg about card
                producer1.send(cardsQueue,"MSG1");
                producer2.send(cardsQueue, "MSG2");
            }

            System.out.println("Placed an information ");

        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
