package event_listener_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaixiaotong on 2016-7-13.
 */
public class MainApp {
    @Autowired
    private OrderService orderService;

    public static void main(String args[]){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        OrderService orderService = (OrderService)applicationContext.getBean("orderService");

        orderService.createOrder(001);
        applicationContext.close();
    }
}
