package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class LoginReqAction extends Action {

    public LoginReqAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_LOGIN_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private String mobile;

    private String password;

}
