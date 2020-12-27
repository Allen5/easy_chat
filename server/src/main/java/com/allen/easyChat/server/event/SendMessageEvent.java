package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.ReceiveMessageNotifyAction;
import com.allen.easyChat.common.action.SendMessageReqAction;
import com.allen.easyChat.common.action.SendMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import com.allen.easyChat.server.connection.ConnectionPool;
import com.allen.easyChat.server.model.Message;
import com.allen.easyChat.server.model.User;
import com.allen.easyChat.server.service.MessageService;
import com.allen.easyChat.server.service.UserService;
import com.allen.easyChat.server.util.SpringContextUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class SendMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        SendMessageReqAction reqAction = JSONObject.parseObject(action.getPayload(), SendMessageReqAction.class);
        System.out.println("send message req: " + reqAction);

        SendMessageRespAction respAction = new SendMessageRespAction();
        respAction.setResult(false);

        // 根据channelId获取到用户id
        Long fromUserId = ConnectionPool.getInstance().getUserId(channel.id().asLongText());
        if ( null == fromUserId ) {
            System.out.println("can not find userId with channelId: " + channel.id().asLongText());
            respAction.setPayload(JSONObject.toJSONString(respAction));
            return respAction;
        }

        // 判断是否存在发送方用户信息
        UserService userService = SpringContextUtil.getBean(UserService.class);
        User fromUser = userService.getById(fromUserId);
        if ( null == fromUser ) {
            System.out.println("can not find user with userId: " + fromUserId);
            respAction.setPayload(JSONObject.toJSONString(respAction));
            return respAction;
        }

        // 判断是否存在接收方用户信息
        User toUser = userService.getById(reqAction.getToUserId());
        if ( null == toUser ) {
            System.out.println("can not find user with userId: " + fromUserId);
            respAction.setPayload(JSONObject.toJSONString(respAction));
            return respAction;
        }

        // 往消息表插入消息数据
        Message message = new Message();
        message.setFromUserId(fromUserId);
        message.setToUserId(toUser.getId());
        message.setMessageType(reqAction.getMessageType());
        message.setContent(reqAction.getMessage());
        message.setSendTimestamp(System.currentTimeMillis());
        MessageService messageService = SpringContextUtil.getBean(MessageService.class);
        message = messageService.add(message);

        // Tips: 可选项。如果在线，就是接收，不在线就是离线消息
        // 找到接收方的链接对象
        // 发送消息
        List<Channel> toChannels = ConnectionPool.getInstance().getChannel(toUser.getId());
        if ( !CollectionUtils.isEmpty(toChannels) ) {
            ReceiveMessageNotifyAction notifyAction = new ReceiveMessageNotifyAction();
            notifyAction.setFromUserId(fromUserId);
            notifyAction.setMessageId(message.getId());
            notifyAction.setMessageType(message.getMessageType());
            notifyAction.setMessage(message.getContent());
            notifyAction.setMobile(fromUser.getMobile());
            notifyAction.setPayload(JSONObject.toJSONString(notifyAction));
            toChannels.stream().forEach(toChannel -> {
                if ( null == toChannel ) {
                    System.out.println("get null toChannel");
                    return ;
                }
                toChannel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(notifyAction)));
            });
        }


        // 返回发送结果
        respAction.setMessageId(message.getId());
        respAction.setResult(true);
        respAction.setPayload(JSONObject.toJSONString(respAction));
        return respAction;
    }

}
