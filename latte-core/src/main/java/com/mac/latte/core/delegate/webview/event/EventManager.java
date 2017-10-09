package com.mac.latte.core.delegate.webview.event;

import java.util.HashMap;

/**
 * Description:${DESC}
 * Author:douliu
 * Date:Created on 2017/9/27.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
        registerEvent(IEvent.ACTION_TEST, new TestEvent());

    }

    private static class Holder{
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    private void registerEvent(String action, Event event) {
        EVENTS.put(action, event);
    }

    public Event createEvent(String action) {
        Event event = EVENTS.get(action);
        if (event == null) {
            return new UnDefineEvent();
        }
        return event;
    }


}
