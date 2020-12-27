package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class FetchOnlineUsersRespAction extends Action {

    public FetchOnlineUsersRespAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_ONLINE_USERS_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    // TODO:

}
