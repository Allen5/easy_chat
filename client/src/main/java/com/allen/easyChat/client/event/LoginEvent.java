package com.allen.easyChat.client.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.LoginRespAction;
import com.allen.easyChat.common.event.IEvent;
import io.netty.channel.Channel;

/**
 * 接收登陆结果
 */
public class LoginEvent implements IEvent<Action, Action> {


    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("receive action: " + action);
        LoginRespAction respAction = JSONObject.parseObject(action.getPayload(), LoginRespAction.class);
        System.out.println("received login action resp: " + respAction);
        return null;
    }

}
