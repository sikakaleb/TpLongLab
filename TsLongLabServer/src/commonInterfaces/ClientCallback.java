package commonInterfaces;

import AdminManagement.Referee;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote, Serializable {
    void receiveResults(Referee results) throws RemoteException;

    void connectToServer(VotingService service) throws RemoteException;
}
