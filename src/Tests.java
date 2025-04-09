import Accounts.AccountConfigurations.DepositPercent;
import Accounts.BaseAccount;
import Accounts.TypeOfBankAccount;
import DateTimeProvider.RewindClock;
import Entities.Bank;
import Exceptions.AccountException;
import Exceptions.BankException;
import Exceptions.TransactionException;
import Interfaces.CentralBank;
import Interfaces.Clients;
import Models.Percent;
import Service.ServiceCentralBank;
import Transaction.BankTransaction;
import Transaction.State;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Tests {

    @Test
    public void transactionTest() throws TransactionException, AccountException, BankException {
        // Создаем центральный банк
        CentralBank cb = new ServiceCentralBank(new RewindClock(LocalDateTime.now(), new ArrayList<>()));

        // Создаем список процентов для депозита
        List<DepositPercent> list = new ArrayList<>();
        list.add(new DepositPercent(new Percent(3), 12)); // Исправлено на создание объекта Percent

        try {
            // Cоздать банк
            Bank bank = cb.createBank("Sberbank", 3, list, 10,
                    200000, -1, Duration.ofDays(90));

            // Проверяем, что bank не равен null
            assertNotNull(bank);

            // Создаем клиента и сохраняем его в cb
            Clients client = cb.registerClient("Artyom", "Batmanov", "KushelevskaiaDoroga", 12345L);

            // Проверяем, что client не равен null
            assertNotNull(client);

            // Проверяем, что bank был успешно создан перед вызовом createAccount

            // Создаем аккаунт
            BaseAccount account1 = bank.createAccount(TypeOfBankAccount.DEBIT, client, Duration.ofDays(90));

            // Проверяем, что account1 не равен null
            assertNotNull(account1);

            // Пополняем счет на 100000
            BankTransaction transaction1 = cb.replenishAccount(bank.getId(), account1.getId(), 10000);
            assertEquals(10000, account1.getBalance());

            // Отменяем транзакцию
            cb.cancelTransaction(bank.getId(), account1.getId(), transaction1.getId());
            assertEquals(State.Canceled, transaction1.getTransactionState());

            // Создаем еще один аккаунт
            BaseAccount account2 = bank.createAccount(TypeOfBankAccount.DEBIT, client, Duration.ofDays(90));

            // Проверяем, что account2 не равен null
            assertNotNull(account2);

            // Пополняем счет на 500
            BankTransaction transaction2 = cb.replenishAccount(bank.getId(), account2.getId(), 500);
            assertEquals(500, account2.getBalance());

            // Отменяем транзакцию
            cb.cancelTransaction(bank.getId(), account2.getId(), transaction2.getId());
            assertEquals(State.Canceled, transaction2.getTransactionState());
        } catch (BankException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
