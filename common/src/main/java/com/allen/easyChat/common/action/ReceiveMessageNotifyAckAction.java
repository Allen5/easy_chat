package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class ReceiveMessageNotifyAckAction extends Action {

    public ReceiveMessageNotifyAckAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_RECEIVE_MESSAGE_NOTIFY_ACK.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    /**
     * 消息id
     */
    private String messageId;

}
