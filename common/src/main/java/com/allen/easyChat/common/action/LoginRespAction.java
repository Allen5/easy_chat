package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class LoginRespAction extends Action {

    public LoginRespAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_LOGIN_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    /**
     * 登陆结果
     */
    private Boolean result;

    /**
     * 当前用户id
     */
    private Long userId;

}
