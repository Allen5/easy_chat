package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class FetchOnlineUsersReqAction extends Action {

    public FetchOnlineUsersReqAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_ONLINE_USERS_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private int page;

    private int count;

}
