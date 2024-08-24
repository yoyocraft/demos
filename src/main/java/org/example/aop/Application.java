package org.example.aop;

import lombok.NonNull;
import org.example.aop.model.SaveOrder;
import org.example.aop.model.UpdateOrder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
@SpringBootApplication
public class Application implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        // 判断下 OrderService 到底是 JDK 代理还是 CGLIB 代理
        OrderService service = applicationContext.getBean(OrderService.class);
        System.out.println(service);
        // 保存订单
        String saveOrderId = "order_id_001";
        orderService.saveOrder(new SaveOrder(saveOrderId));

        // 更新订单
        String updateOrderId = "order_id_002";
        orderService.updateOrder(new UpdateOrder(updateOrderId));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
