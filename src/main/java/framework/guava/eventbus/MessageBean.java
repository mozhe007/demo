package framework.guava.eventbus;

public class MessageBean {

    private final int message;

    public MessageBean(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}
