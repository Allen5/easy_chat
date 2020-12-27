package com.allen.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchOnlineUsersRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class OnlineUserEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchOnlineUsersRespAction respAction = JSONObject.parseObject(action.getPayload(), FetchOnlineUsersRespAction.class);
        System.out.println("receive online users: " + respAction);
        return null;
    }

}
