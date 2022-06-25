package com.example.ms_pro.base.event;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {

    public static void register(Object o) {
        if (!EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().register(o);
        }
    }

    public static void unregister(Object o) {
        if (EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().unregister(o);
        }
    }

    public static boolean hasSubscriber(Class c) {
        return EventBus.getDefault().hasSubscriberForEvent(c);
    }

    public static void post(Object o) {
        try {
            if (hasSubscriber(o.getClass())) {
                EventBus.getDefault().post(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(o);
        }
    }

    public static void postSticky(Object o) {
        try {
            if (hasSubscriber(o.getClass())) {
                EventBus.getDefault().postSticky(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(o);
        }
    }


    public static void removeSticky(Class c) {
        try {
            EventBus.getDefault().removeStickyEvent(EventBus.getDefault().getStickyEvent(c));
        } catch (Exception e) {
        }
    }
}
