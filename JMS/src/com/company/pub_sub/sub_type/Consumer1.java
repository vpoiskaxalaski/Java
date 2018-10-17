package com.company.pub_sub.sub_type;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

import static javax.jms.JMSContext.AUTO_ACKNOWLEDGE;
import static javax.jms.JMSContext.CLIENT_ACKNOWLEDGE;

public class Consumer1  {
    //ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    // JMSConsumer интерфейс с методом для получения сообщения
    JMSConsumer consumer;

    Consumer1() {
        //объекты JMS  соединения и сессии. Нужно соединение с MOM, обмен идет в сессии
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");

            //не долговременная
            Destination priceInfo= context.createTopic("PriceInfo");
            consumer = context.createConsumer(priceInfo);

            Message message = consumer.receive();
            //не удаляет сообщенииз очереди
            ((com.sun.messaging.jms.Message)message).acknowledgeThisMessage();

            System.out.println("consumer1 got the text message: "+
                    message.getBody(String.class));
        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }

    public static  void main (String[] argvs){
        new Consumer1();
    }
}
