package com.company.p2p.async;

import javax.jms.*;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class DirectMessageReceiver implements MessageListener {

    //  ConnectionFactory - объект, который создает объекты Connection инкапсулированные в JMSContext
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();

    // JMSConsumer интерфейс с методом для получения сообщения
    JMSConsumer consumer;

    DirectMessageReceiver() {
        // объекты JMS  соединения и сессии.
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");

            //администрируемый объект, содержащий информацию о конфигурации конкретного поставщика
            Destination cardsQueue = context.createQueue("BankCardQueue");

            consumer = context.createConsumer(cardsQueue);
            consumer.setMessageListener(this);
            System.out.println("Listening to theBankCardQueue...");

            //wait for messages
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onMessage(Message message) {
        try{
            System.out.println("Got the text message from the BankCardQueue: "+
                    message.getBody(String.class));
                    System.out.println("\n " +
                            " = Here's what toString() on the message prints \n"+ message);

        } catch (Exception e) {
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static  void main (String[] argvs){
        new DirectMessageReceiver();
    }
}
