package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.ReceiveMessageNotifyAckAction;
import com.allen.easyChat.common.action.ReceiveMessageNotifyAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class ReceiveMessageEvent implements IEvent<Action, Action> {

    @Override
    public ReceiveMessageNotifyAckAction handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        ReceiveMessageNotifyAckAction notifyAckAction = JSONObject.parseObject(action.getPayload(), ReceiveMessageNotifyAckAction.class);
        System.out.println("received message ack: " + notifyAckAction);

        // TODO: 更新数据库

        return null;
    }

}
