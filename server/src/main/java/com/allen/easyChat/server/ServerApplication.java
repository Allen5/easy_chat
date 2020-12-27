package com.allen.easyChat.server;

import com.allen.easyChat.server.server.WebSocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
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
