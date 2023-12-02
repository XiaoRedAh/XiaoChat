package org.chat.server;

import io.netty.channel.Channel;
import org.chat.server.socket.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author XiaoRed
 * @date 2023/12/1 15:24
 */
@SpringBootApplication
@Configuration
@ImportResource(locations = {"classpath:spring-config.xml"})
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Resource
    private NettyServer nettyServer;

    /**
     * 启动SpringBoot
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 直接用SpringBoot的run方法，默认读取application.yml配置文件
     * 如果需要读取其他配置文件，就要用到这个configure方法，这里额外读取@ImportResource指定的spring-config.xml配置文件
     * spring-config.xml配置异步线程池
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * 应用初始化后，调用这个run方法，利用多线程启动Netty服务端
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("Netty服务端正在启动【port：3000】...");
        //Netty服务端实现了Callable接口，可以把它作为线程任务交给线程池去执行call方法，这样一来，Netty服务端的启动就是异步操作，主线程继续做其它事，等异步任务做完后，主线程再来拿执行结果
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(nettyServer);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("Netty服务端启动失败：channel is null");
        //启动过程中，不断检查channel状态，每0.5s检查一次，直到channel处于channel状态为止
        while (!channel.isActive()) {
            logger.info("Netty服务正在进行连接服务 ...");
            Thread.sleep(500);
        }
        logger.info("服务端连接服务完成 {}", channel.localAddress());
    }

}
