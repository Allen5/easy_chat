package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class SendMessageReqAction extends Action {

    public SendMessageReqAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_SEND_MESSAGE_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    /**
     * 接收消息的用户id
     */
    private Long toUserId;

    /**
     * 消息内容格式
     */
    private String messageType;

    /**
     * 消息内容
     */
    private String message;

}
