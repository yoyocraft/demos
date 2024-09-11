package org.example.lambda;

import java.util.function.Supplier;

/**
 * @author yoyocraft
 * @date 2024/09/10
 */
public class LambdaDemo {

    // static final Supplier<StateCounter> nodeSupplier = new
    //         Supplier<StateCounter>() {
    //             @Override
    //             public StateCounter get() {
    //                 return new StateCounter();
    //             }
    //         };

    static final Supplier<StateCounter> nodeSupplierV2 = StateCounter::new;

    static class StateCounter {
        int val;
        StateCounter next;

        StateCounter() {
        }
    }
}
