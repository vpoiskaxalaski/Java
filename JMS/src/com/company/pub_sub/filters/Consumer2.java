package com.company.pub_sub.filters;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

import static javax.jms.JMSContext.CLIENT_ACKNOWLEDGE;

public class Consumer2 {
    //ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    // JMSConsumer интерфейс с методом для получения сообщения
    JMSConsumer consumer;

    Consumer2() {
        //объекты JMS  соединения и сессии. Нужно соединение с MOM, обмен идет в сессии
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            // администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("Filter");
            consumer = context.createConsumer(cardsQueue);

            //фильтр у receiver
            String selector = "symbol=BSTU";
            context.createConsumer(cardsQueue, selector);

            //TextMessage - объект с Java String
            TextMessage textMessage = (TextMessage) consumer.receive();

            System.out.println("Consumer2 got the text message: "+ textMessage.getText());

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
