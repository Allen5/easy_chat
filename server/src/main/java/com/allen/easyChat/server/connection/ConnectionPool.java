package com.allen.easyChat.server.connection;

import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;


public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private ConnectionPool() {
        this.users = new ConcurrentHashMap<>();
        this.userIds = new ConcurrentHashMap<>();
        this.channels = new ConcurrentHashMap<>();
    }
    public static ConnectionPool getInstance() {
        return INSTANCE;
    }
    /**
     * userId => [channelId, channelId...]
     */
    private ConcurrentHashMap<Long, HashSet<String>> users;

    /**
     * channelId => channel
     */
    private ConcurrentHashMap<String, Channel> channels;

    /**
     * channelId => userId
     */
    private ConcurrentHashMap<String, Long> userIds;

    /**
     * 用户登陆成功之后才会放入链接池
     */
    public void add(final Long userId, final Channel channel) {
        if ( null == userId ) {
            System.out.println("userId is empty!");
            return ;
        }
        if ( null == channel ) {
            System.out.println("channel is empty!");
            return ;
        }

        HashSet<String> channelIds = users.get(userId);
        if ( CollectionUtils.isEmpty(channelIds) ) {
            channelIds = new HashSet<>();
        }
        channelIds.add(channel.id().asLongText());
        userIds.put(channel.id().asLongText(), userId);
        users.put(userId, channelIds);
        channels.put(channel.id().asLongText(), channel);
    }

    public void removeByChannelId(final String channelId) {
        if ( null == channelId || channelId.isEmpty() ) {
            System.out.println("channelId is empty!");
            return ;
        }
        channels.remove(channelId);
        Long userId = userIds.get(channelId);
        if ( null != userId ) {
            users.remove(userId);
            userIds.remove(userId);
        }
    }

    public void removeByUserId(final Long userId) {
        if ( null == userId ) {
            System.out.println("userId is empty!");
            return ;
        }
        HashSet<String> channelIds = users.get(userId);
        if ( !CollectionUtils.isEmpty(channelIds) ) {
            users.remove(userId);
            channelIds.stream().forEach(channelId -> {
                userIds.remove(channelId);
                channels.remove(channelId);
            });
        }
    }

}
