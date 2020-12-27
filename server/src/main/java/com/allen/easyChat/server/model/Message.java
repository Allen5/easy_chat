package com.allen.easyChat.server.model;

import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`message`")
public class Message {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发送用户id
     */
    @Column(name = "`from_user_id`")
    private Long fromUserId;

    /**
     * 接收用户id
     */
    @Column(name = "`to_user_id`")
    private Long toUserId;

    /**
     * 0文本消息 1表情包消息 2语音消息 3图片消息
     */
    @Column(name = "`message_type`")
    private Byte messageType;

    /**
     * 消息内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 0发送成功 1接收成功
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 消息发送时间
     */
    @Column(name = "`send_timestamp`")
    private Long sendTimestamp;

    /**
     * 消息接收时间
     */
    @Column(name = "`recv_timestamp`")
    private Long recvTimestamp;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String FROM_USER_ID = "fromUserId";

    public static final String DB_FROM_USER_ID = "from_user_id";

    public static final String TO_USER_ID = "toUserId";

    public static final String DB_TO_USER_ID = "to_user_id";

    public static final String MESSAGE_TYPE = "messageType";

    public static final String DB_MESSAGE_TYPE = "message_type";

    public static final String CONTENT = "content";

    public static final String DB_CONTENT = "content";

    public static final String STATUS = "status";

    public static final String DB_STATUS = "status";

    public static final String SEND_TIMESTAMP = "sendTimestamp";

    public static final String DB_SEND_TIMESTAMP = "send_timestamp";

    public static final String RECV_TIMESTAMP = "recvTimestamp";

    public static final String DB_RECV_TIMESTAMP = "recv_timestamp";
}