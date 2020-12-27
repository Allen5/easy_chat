package com.allen.easyChat.server;

import com.allen.easyChat.server.server.WebSocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = {
        "com.allen.easyChat.server.mapper"
})
public class ServerApplication implements CommandLineRunner {

    public static void main( String[] args ) {
        new SpringApplication(ServerApplication.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        WebSocketServer server = new WebSocketServer("/chat");
        server.start((short) 8081);
    }
}
