package com.company.p2p.sync;

import javax.jms.*;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

     class MyReceiver{

    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    MyReceiver() {
        //JMSContext - объекты JMS  соединения и сессии.
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            //Место назначения — это администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("BankOrderDestination");
            consumer = context.createConsumer(cardsQueue);

            //Message - интерфейс для всех сообщений. Содержит заголовок и тело
            Message message = consumer.receive();
            System.out.println("Got the text message from the BankCardQueue: "+
                    message.getBody(String.class));
            System.out.println("\n " +
                    " = Here's what toString() on the message prints \n"+ message);
            //wait for messages
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }

    public static  void main (String[] argvs){
        new MyReceiver();
    }
}
