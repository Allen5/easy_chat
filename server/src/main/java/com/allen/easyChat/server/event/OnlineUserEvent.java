package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchOnlineUsersReqAction;
import com.allen.easyChat.common.action.FetchOnlineUsersRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class OnlineUserEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchOnlineUsersReqAction reqAction = JSONObject.parseObject(action.getPayload(), FetchOnlineUsersReqAction.class);
        System.out.println("receive online users: " + reqAction);

        // TODO: 处理业务逻辑

        return null;
    }

}
