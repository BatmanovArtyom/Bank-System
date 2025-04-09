package Accounts;

import Accounts.AccountConfigurations.BankConfiguration;
import Accounts.AccountConfigurations.CreditAccountConfiguration;
import Exceptions.AccountException;
import Exceptions.BankException;
import Exceptions.TransactionException;
import Interfaces.Clients;
import Models.Limit;
import lombok.Getter;
import lombok.Setter;

public class CreditAccount extends BaseAccount {
    private final CreditAccountConfiguration configuration;
    private final Limit limitForDubiousClient;

    public CreditAccount(Clients client, BankConfiguration bankConfiguration) throws BankException {
        super(client, TypeOfBankAccount.CREDIT);
        if (bankConfiguration == null) {
            throw BankException.bankDoesNotExist("Bank configuration cannot be null");
        }
        this.configuration = bankConfiguration.getCreditAccountConfiguration();
        this.limitForDubiousClient = bankConfiguration.getLimitForDubiousClient();
        this.setBalance(bankConfiguration.getCreditAccountConfiguration().getCreditLimit());
    }

    /**
     * Decreases the amount in the account by the specified sum.
     *
     * @param sum the amount to decrease
     * @throws TransactionException if the sum is negative or exceeds the limit for dubious clients
     * @throws AccountException     if there's not enough money in the account
     */
    @Override
    public void decreaseAmount(Integer sum) throws TransactionException, AccountException {
        if (sum <= 0) {
            throw new TransactionException("Negative amount");
        }

        if (getClient().isDubious()) {
            if (sum > limitForDubiousClient.getValue()) {
                throw new TransactionException("Sum exceeding the limit for dubious client");
            }
        }

        if (sum > getBalance()) {
            if (getBalance() - sum + configuration.getCreditCommission() < configuration.getCreditLimit()) {
                throw new AccountException("Not enough money");
            }
        }

        Integer newBalance = (sum <= getBalance()) ? getBalance() - sum : getBalance() - sum + configuration.getCreditCommission();
        setBalance(newBalance);
    }
}
