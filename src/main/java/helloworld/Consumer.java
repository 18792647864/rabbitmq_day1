package helloworld;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

//        //创建连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("47.96.96.91");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("ems");
//
//
//        //创建连接对象
//        Connection connection = connectionFactory.newConnection();

        //通过工具类去获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("mh-queue",true,false,false,null);


        //消费消息
        //参数1：消费哪个队列的消息  队列名称
        //参数2：开启消息的自动确认机制
        //参数3：消费消息的回调接口
        channel.basicConsume("mh-queue",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String (body) = " + new String(body));
            }
        });




    }

}
