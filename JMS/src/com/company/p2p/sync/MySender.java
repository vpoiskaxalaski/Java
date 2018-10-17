package com.company.p2p.sync;

import com.sun.messaging.ConnectionConfiguration;

import javax.jms.*;

public class MySender {
    public static  void  main(String[] argv){
        //ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
        com.sun.messaging.ConnectionFactory factory= new com.sun.messaging.ConnectionFactory();
        //JMSContext - объекты JMS  соединения и сессии.
        try(JMSContext context = factory.createContext("admin", "admin")){
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            //Место назначения — это администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("BankCard");
            //JMSProducer -  интерфейс с методом для посылки сообщения получателю
            JMSProducer producer = context.createProducer();
            producer.send(cardsQueue, "ФЫВАПРОЛ");

            System.out.println("Placed an information about card transaction to Bank ");

        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
