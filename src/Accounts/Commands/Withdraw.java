package Accounts.Commands;

import Exceptions.AccountException;
import Exceptions.TransactionException;
import Accounts.BaseAccount;

import java.math.BigDecimal;

public class Withdraw implements BalanceOperationCommand {
    private final BaseAccount account;
    private final Integer sum;

    public Withdraw(BaseAccount account, Integer sum) throws TransactionException {
        if (sum < 0) {
            throw TransactionException.negativeAmount();
        }
        this.account = account;
        this.sum = sum;
    }

    @Override
    public void execute() throws TransactionException, AccountException {
        account.decreaseAmount(sum);
    }

    @Override
    public void cancel() throws TransactionException {
        account.increaseAmount(sum);
    }
}
