package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.SendMessageReqAction;
import com.allen.easyChat.common.action.SendMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class SendMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        SendMessageReqAction reqAction = JSONObject.parseObject(action.getPayload(), SendMessageReqAction.class);
        System.out.println("send message req: " + reqAction);

        // TODO: 业务处理

        return null;
    }

}
