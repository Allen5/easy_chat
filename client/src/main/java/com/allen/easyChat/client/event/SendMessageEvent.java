package com.allen.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.SendMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class SendMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        SendMessageRespAction respAction = JSONObject.parseObject(action.getPayload(), SendMessageRespAction.class);
        System.out.println("send message resp: " + respAction);
        return null;
    }

}
