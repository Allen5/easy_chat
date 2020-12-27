package com.allen.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchHistoryMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class HistoryMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchHistoryMessageRespAction respAction = JSONObject.parseObject(action.getPayload(), FetchHistoryMessageRespAction.class);
        System.out.println("received history message: " + respAction);
        return null;
    }
}
