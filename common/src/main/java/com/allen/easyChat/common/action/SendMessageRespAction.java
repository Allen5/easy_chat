package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class SendMessageRespAction extends Action {

    public SendMessageRespAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_SEND_MESSAGE_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    /**
     * 发送的消息id
     */
    private Long messageId;

    /**
     * 消息发送结果
     */
    private Boolean result;

}
