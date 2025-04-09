package Models;

import Exceptions.TransactionException;

import java.math.BigDecimal;

public class Limit {
    private final long value;

    public Limit(long limit) throws TransactionException {
        if (limit < 0) {
            this.value = limit;
        } else {
            throw TransactionException.negativeAmount();
        }

    }

    public long getValue() {
        return value;
    }
}
