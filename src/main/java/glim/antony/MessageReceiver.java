package glim.antony;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.logging.Logger;

public class MessageReceiver {

    private static final Logger log = Logger.getLogger(MessageReceiver.class.getName());

    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL; // default broker URL is : tcp://localhost:61616"
    private static String JMS_QUEUE_NAME = "MyJmsQueueName";

    public static String receive() throws JMSException {

        Connection connection = new ActiveMQConnectionFactory(URL).createConnection();
        connection.start();

        // Creating session for seding messages
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(JMS_QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();

        String xml = null;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            xml = textMessage.getText();
            log.info("message received: " + xml);
        }
        connection.close();
        return xml;
    }
}
