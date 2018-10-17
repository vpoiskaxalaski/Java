package com.company.p2p.async;

import javax.jms.*;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class DirectMessageSender {
    public static void main(String[] args){
        //ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
        ConnectionFactory factory= new ConnectionFactory();

        //объекты JMS  соединения и сессии. Нужно соединение с MOM, обмен идет в сессии
        try(JMSContext context = factory.createContext("admin", "admin")){
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

            // администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("BankCardQueue");

            // JMSProducer интерфейс с методом для посылки сообщения получателю
            JMSProducer producer = context.createProducer();
            producer.send(cardsQueue, "SAM 100 5634334");

            System.out.println("Placed an information about card transaction to Bank ");

        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
