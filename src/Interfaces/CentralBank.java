package Interfaces;

import Accounts.AccountConfigurations.*;
import DateTimeProvider.*;
import Entities.*;
import Exceptions.AccountException;
import Exceptions.BankException;
import Exceptions.TransactionException;
import Transaction.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CentralBank {
    RewindClock getRewindClock();

    Clients registerClient(String name, String surname, String address, Long passport);
    Bank findBankByName(String name) throws BankException;

    BankTransaction withdrawMoney(UUID bankId, UUID accountId, double amount) throws TransactionException, AccountException, BankException;
    BankTransaction transferMoney(UUID bankId1, UUID accountId1, UUID bankId2, UUID accountId2, double amount) throws AccountException, TransactionException, BankException;
    Bank createBank(String name, double debitPercent, List<DepositPercent> depositPercents,
                    double creditCommission, double creditLimit, double limitForDubiousClient,
                    Duration endOfPeriod) throws BankException, AccountException, TransactionException;

    BankTransaction replenishAccount(UUID bankId, UUID accountId, double amount) throws TransactionException, AccountException, BankException;

    void cancelTransaction(UUID bankId, UUID accountId, UUID transactionId) throws AccountException, TransactionException, BankException;
}
