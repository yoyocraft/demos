package org.example.aop.log;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
public class OperateLog {

    private String orderId;

    private String desc;

    private String result;

    public OperateLog() {
    }

    public OperateLog(String orderId) {
        this.orderId = orderId;
    }

    public OperateLog(String orderId, String desc, String result) {
        this.orderId = orderId;
        this.desc = desc;
        this.result = result;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
