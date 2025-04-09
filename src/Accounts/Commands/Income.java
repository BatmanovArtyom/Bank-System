package Accounts.Commands;

import Exceptions.AccountException;
import Exceptions.TransactionException;
import Accounts.BaseAccount;

import java.math.BigDecimal;

public class Income implements BalanceOperationCommand {
    private final BaseAccount account;
    private final Integer sum;

    public Income(BaseAccount account, Integer sum) throws TransactionException {
        this.account = account;
        if (sum < 0) {
            throw TransactionException.negativeAmount();
        }
        this.sum = sum;
    }

    @Override
    public void execute() throws TransactionException {
        account.increaseAmount(sum);
    }

    @Override
    public void cancel() throws TransactionException, AccountException {
        account.decreaseAmount(sum);
    }
}
