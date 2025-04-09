package Accounts.Commands;

import Exceptions.AccountException;
import Exceptions.TransactionException;

public interface BalanceOperationCommand extends Command {
    void cancel() throws TransactionException, AccountException;
}
