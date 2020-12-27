package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class FetchHistoryMessageReqAction extends Action {

    public FetchHistoryMessageReqAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_HISTORY_MESSAGE_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    /**
     * 接收消息的用户id
     */
    private Long userId;

}
