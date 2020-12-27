package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchOnlineUsersReqAction;
import com.allen.easyChat.common.action.FetchOnlineUsersRespAction;
import com.allen.easyChat.common.event.IEvent;
import com.allen.easyChat.common.vo.UserItem;
import com.allen.easyChat.server.connection.ConnectionPool;
import com.allen.easyChat.server.model.User;
import com.allen.easyChat.server.service.UserService;
import com.allen.easyChat.server.util.SpringContextUtil;
import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OnlineUserEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("received action: " + action);
        FetchOnlineUsersReqAction reqAction = JSONObject.parseObject(action.getPayload(), FetchOnlineUsersReqAction.class);
        System.out.println("receive online users: " + reqAction);

        List<Long> userIds = ConnectionPool.getInstance().listUserIds();
        UserService userService = SpringContextUtil.getBean(UserService.class);
        List<User> users = userService.listUsers(userIds);

        FetchOnlineUsersRespAction respAction = new FetchOnlineUsersRespAction();
        if ( !CollectionUtils.isEmpty(users) ) {
            respAction.setUsers(users.stream().map(user -> {
                UserItem item = new UserItem();
                item.setUserId(user.getId());
                item.setMobile(user.getMobile());
                return item;
            }).collect(Collectors.toList()));
        }
        respAction.setPayload(JSONObject.toJSONString(respAction));
        return respAction;
    }

}
