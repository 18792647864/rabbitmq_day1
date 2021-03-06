package fanout;

import com.rabbitmq.client.*;
import com.rabbitmq.tools.json.JSONUtil;
import org.w3c.dom.ls.LSOutput;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过绑定交换机或者直接定义一个交换机
        channel.exchangeDeclare("mh-log","fanout");

        //临时队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queueName,"mh-logs","");

        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });


    }
}
