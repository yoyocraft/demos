package org.example.tx;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author yoyocraft
 * @date 2024/08/31
 */
public class TransactionUtils {

    public static void doAfterTransaction(Runnable action) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new DoTransactionCompletion(action));
        }
    }
}
