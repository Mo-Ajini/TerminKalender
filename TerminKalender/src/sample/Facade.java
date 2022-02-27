package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface zum Login durch username und Passwort
 */

public interface Facade extends Remote{
    public String login(String username, String password) throws RemoteException;
}
