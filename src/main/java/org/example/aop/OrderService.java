package org.example.aop;

import org.example.aop.model.SaveOrder;
import org.example.aop.model.UpdateOrder;

/**
 * @author yoyocraft
 * @date 2024/08/20
 */
public interface OrderService {
    Boolean saveOrder(SaveOrder saveOrder);

    Boolean updateOrder(UpdateOrder updateOrder);
}
