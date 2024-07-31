package org.example.aop.model;

/**
 * @author yoyocraft
 * @date 2024/07/31
 */
public class SaveOrder {

    private String id;

    public SaveOrder(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
