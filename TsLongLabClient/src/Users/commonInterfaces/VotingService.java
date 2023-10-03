package Users.commonInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VotingService extends Remote {
    List<ICandidate> getCandidates() throws RemoteException;
}
