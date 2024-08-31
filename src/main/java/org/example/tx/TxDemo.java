package org.example.tx;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author yoyocraft
 * @date 2024/08/31
 */
public class TxDemo {

    @Transactional
    public void doTx() {
        // start tx

        TransactionUtils.doAfterTransaction(() -> {
            // do something, like rpc/mq/...
        });

        // end tx
    }
}
