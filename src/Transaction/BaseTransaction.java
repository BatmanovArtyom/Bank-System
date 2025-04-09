package Transaction;

import Accounts.Commands.BalanceOperationCommand;

public class BaseTransaction extends BankTransaction {
    public BaseTransaction(BalanceOperationCommand command) {
        super(command);
    }
}
