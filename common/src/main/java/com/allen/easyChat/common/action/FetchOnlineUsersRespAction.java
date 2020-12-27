package com.allen.easyChat.common.action;

import com.allen.easyChat.common.vo.UserItem;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
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

    @Setter
    private List<UserItem> users;

}
