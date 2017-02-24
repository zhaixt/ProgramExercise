package event_listener_spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by zhaixiaotong on 2016-7-13.
 */
//@Component("orderService")
public class OrderService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void createOrder(int orderId){
        System.out.println("Order Created");
        OrderEvent event = new OrderEvent(this, orderId);
        System.out.println("Publishing Order Event");
        publisher.publishEvent(event);
    }
}
