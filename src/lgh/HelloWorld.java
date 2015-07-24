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
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("start");

        String connStr = "amqp://guest:guest@localhost:5672/%2f";
        String exchangeName = "amq.topic";
        String queueName = "testQueue1";
        String routingKey = "a.b"; // msg topic

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setNetworkRecoveryInterval(10000);
            factory.setAutomaticRecoveryEnabled(true);
            factory.setUri(connStr);

            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);

            boolean autoAck = false;
            channel.basicConsume(queueName, autoAck, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
                {
                    String routingKey = envelope.getRoutingKey();

                    System.out.print("routingKey: ");
                    System.out.println(routingKey);
                    System.out.print("body: ");
                    System.out.println(new String(body));
                    System.out.println();

                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                }
                @Override
                public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) 
                {
                    System.out.println("consumer exception occured!");
                    //sig.printStackTrace();
                }
            });

            Thread.sleep(1000000);

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
