package com.company.pub_sub.filters;

import com.sun.messaging.ConnectionConfiguration;

import javax.jms.*;

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
            Destination ordersQueue=context.createQueue("Filter");

            // JMSProducer интерфейс с методом для посылки сообщения получателю
            JMSProducer producer = context.createProducer();

            //TextMessage - объект с Java String
            TextMessage outMsg = context.createTextMessage();
            outMsg.setText("PNV 100 2536721");
            outMsg.setStringProperty("symbol", "BSTU");
            producer.send(ordersQueue, outMsg);
            System.out.println("Placed an information ");

        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
