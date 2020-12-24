package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangName = "mh-topics";
        channel.exchangeDeclare(exchangName,"topic");

        String routekey = "user.save";

        channel.basicPublish(exchangName,routekey,
                null,("这里时topic动态路由模型，routekey：["+routekey+"]").getBytes());

        RabbitMQUtils.closeConnectionAndChannl(channel,connection);

    }
}
