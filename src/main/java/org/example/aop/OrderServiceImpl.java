package org.example.aop;

import org.example.aop.aspect.RecordOperateLog;
import org.example.aop.converter.SaveOrderConverter;
import org.example.aop.converter.UpdateOrderConverter;
import org.example.aop.model.SaveOrder;
import org.example.aop.model.UpdateOrder;
import org.springframework.stereotype.Service;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @RecordOperateLog(desc = "创建订单", converter = SaveOrderConverter.class)
    public Boolean saveOrder(SaveOrder saveOrder) {
        System.out.println("save order, order id: " + saveOrder.getId());
        return Boolean.TRUE;
    }

    @Override
    @RecordOperateLog(desc = "更新订单", converter = UpdateOrderConverter.class)
    public Boolean updateOrder(UpdateOrder updateOrder) {
        System.out.println("update order, order id: " + updateOrder.getOrderId());
        return Boolean.TRUE;
    }

}
