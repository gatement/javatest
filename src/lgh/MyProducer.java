package lgh;

import java.io.IOException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

// RabbitMQ 3.4.2
public class MyProducer {
    public static void main(String[] args) {
        System.out.println("start");

        //String connStr = "amqp://aos:AOSmith@m2mv4.iotsdk.com:5672/%2f";
        String connStr = "amqp://guest:guest@localhost:5672/%2f";
        String queueName = "testQueue2";

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setNetworkRecoveryInterval(10000);
            factory.setAutomaticRecoveryEnabled(true);
            factory.setUri(connStr);

            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();

            // just need to run one time
            channel.queueDeclare(queueName, true, false, false, null);

            byte[] messageBodyBytes = "Hello, world!".getBytes();
            channel.basicPublish("", queueName, null, messageBodyBytes);

            channel.close();
            conn.close();
        }
        catch (Exception ex) {
            System.out.println("exception");
            ex.printStackTrace();
        }

        System.out.println("done");
    }
}
