package event_listener_spring;

import org.springframework.context.event.EventListener;

/**
 * Created by zhaixiaotong on 2016-7-13.
 */
//@Component("orderNotification")
public class OrderNotification {
    private int notificationId;
    public int getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }
    @EventListener
    public void processOrderEvent(OrderEvent event) {
        System.out.println("OrderEvent for Order Id : " + event.getOrderId());
    }
}
