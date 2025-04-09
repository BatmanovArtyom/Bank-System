package Interfaces;

import Exceptions.ClientException;

import java.util.Collection;
import java.util.UUID;

public interface Clients extends Observer.Observer<String> {
    UUID getId();
    String getName();
    String getSurname();
    String getAddress();
    long getPassportNumber();

    void setPassportNumber(Long passportNumber) throws ClientException;

    boolean isDubious();
    Collection<String> getUpdatesOfAccountsConfiguration();
    void setAddress(String address) throws ClientException;
    void setPassportNumber(long passport);
}
