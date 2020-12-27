package com.allen.easyChat.common.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MessageItem {

    private Long messageId;

    private Long fromUserId;

    private Long toUserId;

    private Byte messageType;

    private String message;

    private Byte status;

    private Long updatedTimestamp;

}
