package com.allen.easyChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.allen.easyChat.common.action.Action;
import com.allen.easyChat.common.action.FetchHistoryMessageReqAction;
import com.allen.easyChat.common.action.FetchHistoryMessageRespAction;
import com.allen.easyChat.common.event.IEvent;
import com.allen.easyChat.common.vo.MessageItem;
import com.allen.easyChat.server.model.Message;
import com.allen.easyChat.server.service.MessageService;
import com.allen.easyChat.server.util.SpringContextUtil;
import io.netty.channel.Channel;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryMessageEvent implements IEvent<Action, Action> {

    @Override
    public Action handle(Action action, Channel channel) {

        System.out.println("received action: " + action);
        FetchHistoryMessageReqAction reqAction = JSONObject.parseObject(action.getPayload(), FetchHistoryMessageReqAction.class);
        System.out.println("received history message req: " + reqAction);

        FetchHistoryMessageRespAction respAction = new FetchHistoryMessageRespAction();

        // 只要那到fromUserId == 传入的 或者 toUserId 等于传入的消息列表
        MessageService messageService = SpringContextUtil.getBean(MessageService.class);
        // TODO: 分页优化处理
        List<Message> messages = messageService.fetchHistory(reqAction.getUserId());
        if ( CollectionUtils.isEmpty(messages) ) {
            System.out.println("no history message for userId: " + reqAction.getUserId());
            return respAction;
        }

        respAction.setItems(messages.stream().map(message -> {
            MessageItem messageItem = new MessageItem();
            BeanUtils.copyProperties(message, messageItem);
            messageItem.setMessageId(message.getId());
            messageItem.setMessage(message.getContent());
            messageItem.setUpdatedTimestamp(message.getRecvTimestamp());
            if (null == message.getRecvTimestamp() || message.getRecvTimestamp() == 0) {
                messageItem.setUpdatedTimestamp(message.getSendTimestamp());
            }
            return messageItem;
        }).collect(Collectors.toList()));

        respAction.setPayload(JSONObject.toJSONString(respAction));
        return respAction;
    }
}
