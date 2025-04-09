package Accounts.Commands;

import Exceptions.AccountException;
import Exceptions.TransactionException;

public interface Command {
    void execute() throws TransactionException, AccountException;
}
