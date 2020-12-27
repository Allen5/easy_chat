package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchHistoryMessageReqAction;
import com.allen.easyChat.common.action.FetchHistoryMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

public class HistoryMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchHistoryMessageReqAction reqAction = JSONObject.parseObject(action.getPayload(), FetchHistoryMessageReqAction.class);
        System.out.println("received history message req: " + reqAction);

        // TODO: 处理业务逻辑

        return null;
    }
}
