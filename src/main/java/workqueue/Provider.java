package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        //发布消息
        for (int i = 0;i < 20; i++) {
            channel.basicPublish("", "work", MessageProperties.PERSISTENT_TEXT_PLAIN, ("hello workqueue"+i).getBytes());
        }

        RabbitMQUtils.closeConnectionAndChannl(channel,connection);


    }


}
