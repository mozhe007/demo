package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int id;
    public int lastMessage = 0;

    public EventListener(int id) {
        this.id = id;
    }

    @Subscribe
    public void listen(MessageBean event) {
        lastMessage = event.getMessage();
        System.out.println(String.valueOf(id) + ":" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        EventListener listener1 = new EventListener(1);
        EventListener listener2 = new EventListener(2);
        eventBus.register(listener1);
        eventBus.register(listener2);
        eventBus.post(new MessageBean(200));
        eventBus.post(new MessageBean(300));
    }
}
