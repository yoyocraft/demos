package org.example.tx;

import org.springframework.transaction.support.TransactionSynchronization;

/**
 * 事务完成之后要执行的操作
 *
 * @author yoyocraft
 * @date 2024/08/31
 * @see org.springframework.transaction.support.TransactionSynchronization
 */
public class DoTransactionCompletion implements TransactionSynchronization {

    private final Runnable actionAfterTransactionCompletion;

    public DoTransactionCompletion(Runnable action) {
        this.actionAfterTransactionCompletion = action;
    }

    @Override
    public void afterCompletion(int status) {
        if (status == TransactionSynchronization.STATUS_COMMITTED) {
            // 事务提交之后执行的动作
            this.actionAfterTransactionCompletion.run();
        }
    }
}
