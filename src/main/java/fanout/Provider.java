package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //用通道声明交换机
        //参数1：交换机名称
        //参数2：交换机类型 fanout 广播类型
        channel.exchangeDeclare("mh-logs","fanout");

        //发送消息
        channel.basicPublish("mh-logs","",null,"fanout type message".getBytes());

        //释放资源
        RabbitMQUtils.closeConnectionAndChannl(channel,connection);

    }
}
