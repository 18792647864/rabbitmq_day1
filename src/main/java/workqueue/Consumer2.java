package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer2 {


    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();


        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只能消费一个消息
        channel.queueDeclare("work",true,false,false,null);

        //参数1: 队列名称
        //参数2：消息自动确认 true 消息自动向rabbitmq确认消息消费 false 不会自动确认消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2："+ new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
