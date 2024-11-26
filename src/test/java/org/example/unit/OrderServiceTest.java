package org.example.unit;

import com.google.common.collect.Lists;
import org.example.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 测试目标：
 * 1. 覆盖正常情况
 * 2. 考虑异常和边界情况
 */
public class OrderServiceTest extends BaseUnitTest {

    @Test
    public void calculateTotal_shouldReturnCorrectAmount() {
        Order order = new Order();
        order.setItems(Lists.newArrayList(
                new Item(100.0, 2), // 200
                new Item(50.0, 3)   // 150
        ));

        OrderService service = new OrderService();
        double total = service.calculateTotal(order);

        Assert.assertEquals(350.0, total, 0.01);
    }


    @Test
    public void calculateTotal_withDiscount_shouldApplyCorrectly() {
        Order order = new Order();
        order.setItems(Lists.newArrayList(
                new Item(100.0, 2), // 200
                new Item(50.0, 3)   // 150
        ));
        order.setDiscount(amount -> amount > 200 ? 50 : 0);

        OrderService service = new OrderService();
        double total = service.calculateTotal(order);

        Assert.assertEquals(300.0, total, 0.01);
    }

    @Test
    public void calculateTotal_emptyOrder_shouldThrowException() {
        Order order = new Order();
        OrderService service = new OrderService();

        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            service.calculateTotal(order);
        });

        Assert.assertEquals("Order or items cannot be null/empty", exception.getMessage());
    }

    @Test
    public void calculateTotal_negativeTotal_shouldReturnZero() {
        Order order = new Order();
        order.setItems(Lists.newArrayList(
                new Item(100.0, 1)
        ));
        order.setDiscount(amount -> 150);

        OrderService service = new OrderService();
        double total = service.calculateTotal(order);

        Assert.assertEquals(0.0, total, 0.01);
    }

    @Test
    public void calculateTotal_mockDiscount_shouldApplyCorrectly() {
        Order order = new Order();
        order.setItems(Lists.newArrayList(
                new Item(100.0, 2), // 200
                new Item(50.0, 3)   // 150
        ));
        Discount mockDiscount = Mockito.mock(Discount.class);
        Mockito.when(mockDiscount.apply(350.0)).thenReturn(70.0);
        order.setDiscount(mockDiscount);

        OrderService service = new OrderService();
        double total = service.calculateTotal(order);

        Assert.assertEquals(280.0, total, 0.01);
        // 测试 apply 方法有没有被正确调用
        Mockito.verify(mockDiscount).apply(350.0);
    }
}
