package com.allen.easyChat.client;

import com.allen.easyChat.client.client.WebSocketClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static void main( String[] args ) {
        new SpringApplication(ClientApplication.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        this.connect();

        System.out.println("wait input command");
        while (true ) {
            String command = readCommand();
            if ( null != command &&  command.equals("exit") ) {
                System.exit(-1);
                return ;
            }
        }
    }

    private String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void connect() {
        URI uri = URI.create("ws://localhost:8081/chat");
        WebSocketClient webSocketClient = new WebSocketClient(uri);
        webSocketClient.connect();
    }

}
