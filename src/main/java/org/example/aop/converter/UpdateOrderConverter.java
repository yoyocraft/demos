package org.example.aop.converter;

import org.example.aop.log.OperateLog;
import org.example.aop.model.UpdateOrder;

/**
 * @author yoyocraft
 * @date 2024/08/01
 */
public class UpdateOrderConverter implements OperateLogConverter<UpdateOrder> {

    @Override
    public OperateLog convert(UpdateOrder updateOrder) {
        return new OperateLog(updateOrder.getOrderId());
    }
}
