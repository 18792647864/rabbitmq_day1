package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "mh-logs-direct";

        channel.exchangeDeclare(exchangeName,"direct");


        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        //基于route key绑定 队列和交换机
        channel.queueBind(queue,exchangeName,"error");

        //获取消费的消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });

    }
}
