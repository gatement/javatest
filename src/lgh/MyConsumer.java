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
public class MyConsumer {
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

            channel.queueDeclare(queueName, true, false, false, null);

            channel.basicQos(1, true);

            boolean autoAck = false;
            channel.basicConsume(queueName, autoAck, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
                {
                    System.out.print("body: ");
                    String bodyStr = new String(body);
                    System.out.println(bodyStr);
                    System.out.println();

                    // process the msg

                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                }

                @Override
                public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) 
                {
                    System.out.println("consumer exception occured!");
                    sig.printStackTrace();
                }
            });

            Thread.sleep(1000000000);

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
