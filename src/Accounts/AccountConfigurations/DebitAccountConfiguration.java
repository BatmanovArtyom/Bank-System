package Accounts.AccountConfigurations;

import Exceptions.TransactionException;
import Models.Percent;
import lombok.Getter;
import lombok.Setter;

public class DebitAccountConfiguration {
    @Getter
    @Setter
    private Percent debitPercent;

    public DebitAccountConfiguration(Percent debitPercent) {
        this.debitPercent = debitPercent;
    }

    public void setDebitPercent(Integer percent) throws TransactionException {
        this.debitPercent = new Percent(percent);
    }
}
