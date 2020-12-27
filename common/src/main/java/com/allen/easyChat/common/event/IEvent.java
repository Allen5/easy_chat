package com.allen.easyChat.common.event;

import io.netty.channel.Channel;

public interface IEvent<T, R> {

    /**
     * 处理事件业务
     * @param request
     * @param channel
     * @return
     */
    R handle(final T request, final Channel channel);

}
