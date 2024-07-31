package org.example.aop;

import org.example.aop.model.SaveOrder;
import org.example.aop.model.UpdateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        // 保存订单
        String saveOrderId = "order_id_001";
        orderService.saveOrder(new SaveOrder(saveOrderId));

        // 更新订单
        String updateOrderId = "order_id_002";
        orderService.updateOrder(new UpdateOrder(updateOrderId));
    }
}
