package com.allen.easyChat.common.action;

import com.allen.easyChat.common.vo.MessageItem;
import lombok.Data;
import lombok.ToString;

import java.util.List;
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

    private List<MessageItem> items;

}
