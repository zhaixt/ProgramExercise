package event_listener_spring;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhaixiaotong on 2016-7-13.
 */
public class OrderEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private int orderId;

    public OrderEvent(Object source, int id) {
        super(source);
        this.orderId = id;
        System.out.println("Order Event Created!!");
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;

    }
}
