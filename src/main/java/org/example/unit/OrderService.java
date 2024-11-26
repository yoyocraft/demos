package org.example.unit;

public class OrderService {
    public double calculateTotal(Order order) {
        if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order or items cannot be null/empty");
        }

        // 计算商品金额
        double subtotal = order.getItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // 应用折扣
        if (order.getDiscount() != null) {
            subtotal -= order.getDiscount().apply(subtotal);
        }

        // 返回总金额（保证非负）
        return Math.max(subtotal, 0);
    }
}
