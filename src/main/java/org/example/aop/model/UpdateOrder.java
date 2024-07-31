package org.example.aop.model;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
public class UpdateOrder {

    private String orderId;

    public UpdateOrder(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
