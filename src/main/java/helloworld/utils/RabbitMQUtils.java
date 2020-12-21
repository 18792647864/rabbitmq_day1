package helloworld.utils;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static{

        //重量级资源，类加载的时候执行一次


        //创建连接对象
        //创建连接mq的连接工厂
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("47.96.96.91");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
    }

    //定义提供连接对象的方法
    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChannl(Channel channel,Connection connection){
        try {
            if(null != channel){
                channel.close();
            }
            if(null != connection){
                connection.close();
            }

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }


}
