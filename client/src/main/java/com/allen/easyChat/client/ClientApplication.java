package com.allen.easyChat.client;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.client.client.WebSocketClient;
import com.allen.easyChat.client.event.*;
import com.allen.easyChat.common.action.*;
import com.allen.easyChat.common.event.EventPool;
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

    private WebSocketClient client;

    public static void main( String[] args ) {
        new SpringApplication(ClientApplication.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        this.registeEvent();

        this.connect();

        this.handleCommand();

    }

    private void registeEvent() {
        // 注册登陆响应
        EventPool.getInstance().registe(ActionIdEnum.ACTION_LOGIN_RESP.getAction(), new LoginEvent());
        EventPool.getInstance().registe(ActionIdEnum.ACTION_FETCH_ONLINE_USERS_RESP.getAction(), new OnlineUserEvent());
        EventPool.getInstance().registe(ActionIdEnum.ACTION_SEND_MESSAGE_RESP.getAction(), new SendMessageEvent());
        EventPool.getInstance().registe(ActionIdEnum.ACTION_RECEIVE_MESSAGE_NOTIFY.getAction(), new ReceiveMessageEvent());
        EventPool.getInstance().registe(ActionIdEnum.ACTION_FETCH_HISTORY_MESSAGE_RESP.getAction(), new HistoryMessageEvent());
    }


    private void connect() {
        URI uri = URI.create("ws://localhost:8081/chat");
        this.client = new WebSocketClient(uri);
        this.client.connect();
    }

    private String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void handleCommand() {
        System.out.println("wait input command");
        while (true ) {
            String command = readCommand();
            if ( null == command || command.isEmpty() ) {
                System.out.println("empty command!");
                continue;
            }
            if ( command.equals("exit") ) {
                System.exit(-1);
                return ;
            }
            // login 处理登陆逻辑。其实发送登陆包
            // login mobile password
            if ( command.indexOf("login") == 0 ) {
                String[] params = command.split(" ");
                if ( params.length != 3 ) {
                    System.out.println("need input mobile and password!");
                    continue;
                }
                login(params[1], params[2]);
                continue;
            }

            // 获取在线列表。查看在线的用户信息
            // online page[从1开始], count
            if ( command.indexOf("online") == 0 ) {
                String[] params = command.split(" ");
                if ( params.length != 3 ) {
                    System.out.println("need input page and count!");
                    continue;
                }
                fetchOnlineUsers(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
                continue;
            }

            // 发送消息
            // chat userId message
            if ( command.indexOf("chat") == 0 ) {
                String[] params = command.split(" ");
                if ( params.length != 3 ) {
                    System.out.println("need input userId and message!");
                    continue;
                }
                sendMessage(Long.parseLong(params[1]), params[2]);
                continue;
            }

            // 查看历史聊天记录
            // history userId
            if ( command.indexOf("history") == 0 ) {
                String[] params = command.split(" ");
                if ( params.length != 2 ) {
                    System.out.println("need input userId!");
                    continue;
                }
                fetchHistoryMessage(Long.parseLong(params[1]));
            }
        }
    }

    private void login(final String mobile, final String password) {
        LoginReqAction action = new LoginReqAction();
        action.setMobile(mobile);
        // TODO: 对密码做MD5处理
        action.setPassword(password);
        this.client.send(action, JSONObject.toJSONString(action));
    }

    private void fetchOnlineUsers(final int page, final int count) {
        FetchOnlineUsersReqAction action = new FetchOnlineUsersReqAction();
        action.setPage(page);
        action.setCount(count);
        this.client.send(action, JSONObject.toJSONString(action));
    }

    private void sendMessage(final long userId, final String message) {
        SendMessageReqAction action = new SendMessageReqAction();
        action.setToUserId(userId);
        action.setMessageType((byte) 0);
        action.setMessage(message);
        this.client.send(action, JSONObject.toJSONString(action));
    }

    private void fetchHistoryMessage(final long userId) {
        FetchHistoryMessageReqAction action = new FetchHistoryMessageReqAction();
        action.setUserId(userId);
        this.client.send(action, JSONObject.toJSONString(action));
    }

}
