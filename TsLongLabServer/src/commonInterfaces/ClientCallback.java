package commonInterfaces;

import AdminManagement.Referee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void receiveResults(Referee results) throws RemoteException;
}
