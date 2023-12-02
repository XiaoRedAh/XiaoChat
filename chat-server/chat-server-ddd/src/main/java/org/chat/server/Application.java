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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Netty服务端正在启动【port：3000】...");
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(nettyServer);
        Channel channel = future.get();
        if (null == channel) throw new RuntimeException("Netty服务端启动失败：channel为null");

        while (!channel.isActive()) {
            logger.info("Netty服务端启动中 ...");
            Thread.sleep(500);
        }
    }

}
