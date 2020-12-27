package com.allen.easyChat.common.event;

import java.util.concurrent.ConcurrentHashMap;

public class EventPool {

    private static final EventPool INSTANCE = new EventPool();
    private EventPool() {
        events = new ConcurrentHashMap<>();
    }
    public static EventPool getInstance() {
        return INSTANCE;
    }

    private ConcurrentHashMap<String, IEvent> events;

    /**
     * 事件注册
     * @param key
     * @param handler
     */
    public void registe(final String key, final IEvent handler) {
        if ( null == key || key.isEmpty() ) {
            System.out.println("key is empty!");
            return ;
        }
        if ( null == handler ) {
            System.out.println("handler is empty!");
            return ;
        }
        events.put(key, handler);
    }

    /**
     * 查找事件对象
     * @param key
     * @return
     */
    public IEvent find(final String key) {
        if ( null == key || key.isEmpty() ) {
            System.out.println("key is empty!");
            return null;
        }
        return events.get(key);
    }


}
