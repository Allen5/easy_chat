package com.allen.easyChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * 登陆请求action
 */
@Data
@ToString
public class FetchHistoryMessageRespAction extends Action {

    public FetchHistoryMessageRespAction() {
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_HISTORY_MESSAGE_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    // TODO: 根据业务数据做处理

}
