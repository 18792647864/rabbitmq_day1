package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import javax.swing.text.Style;
import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-1："+ new String(body));
                //参数1：确认队列中的具体时那个消息的标志
                //参数2：是否开启多消息确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
