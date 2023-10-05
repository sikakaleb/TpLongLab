package commonInterfaces;

import AdminManagement.Referee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ClientCallback extends Remote {
    void receiveResults(Referee results) throws RemoteException;

}
