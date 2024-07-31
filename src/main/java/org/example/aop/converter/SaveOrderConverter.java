package org.example.aop.converter;

import org.example.aop.log.OperateLog;
import org.example.aop.model.SaveOrder;

/**
 * @author yoyocraft
 * @date 2024/08/01
 */
public class SaveOrderConverter implements OperateLogConverter<SaveOrder> {

    @Override
    public OperateLog convert(SaveOrder saveOrder) {
        return new OperateLog(saveOrder.getId());
    }
}
