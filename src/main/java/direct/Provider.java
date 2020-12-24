package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接通道对象
        Channel channel = connection.createChannel();

        String exchangeName = "mh-logs-direct";
        //通过通道声明交换机 参数1：交换机名称 参数2：direct 路由模式
        channel.exchangeDeclare(exchangeName,"direct");

        //info error warning 三种， Consumer1 只会接受routingKey 为error 的信息，Consumer1 三者都会接受
        String routingKey = "error";
        channel.basicPublish(exchangeName,routingKey,
                null,("这是direct模型发布的基于rout key：["+routingKey+"]").getBytes());


        RabbitMQUtils.closeConnectionAndChannl(channel,connection);


    }
}
