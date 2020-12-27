package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.LoginReqAction;
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
        LoginReqAction reqAction = JSONObject.parseObject(action.getPayload(), LoginReqAction.class);
        System.out.println("received login action req: " + reqAction);

        // TODO: 登陆逻辑处理

        return null;
    }

}
