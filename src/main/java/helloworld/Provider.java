package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {


    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
//        //创建连接mq的连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //设置连接rabbitmq主机
//        connectionFactory.setHost("47.96.96.91");
//        //设置端口号
//        connectionFactory.setPort(5672);
//        //设置连接哪个虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        //设置访问虚拟主机的用户名和密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("ems");
//
//        //获取连接对象
//        Connection connection = connectionFactory.newConnection();

        //通过工具类去获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //获取连接中的通道
        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数1：队列名称
        //参数2：用来定义队列特性是否要持久化，  true代表持久化，  false 不持久化
        //参数3：exclusive 是否独占队列 true代表不可以可以被其他连接所使用，false表示可以被其他连接所使用
        //参数4：autoDelete 是否在消费完成后自动删除队列，  true 自动删除  false不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("mh-queue",true,false,false,null);

        //发布消息
        //参数1：交换机名称
        //参数2：队列名称
        //参数3：传递消息的额外设置
        //参数4：消息体， 要求字节类型
        channel.basicPublish("","mh-queue", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());

        RabbitMQUtils.closeConnectionAndChannl(channel,connection);


    }
}
