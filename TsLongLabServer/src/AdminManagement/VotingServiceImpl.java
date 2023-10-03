package AdminManagement;

import Users.Candidates.Candidate;
import commonInterfaces.ICandidate;
import commonInterfaces.VotingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    // Charger des données sérialisées lors de l'initialisation
    private AdminApp adminApp;

    public VotingServiceImpl() throws RemoteException {
        super();
        adminApp = new AdminApp(); // Cette ligne chargera vos données lors de l'initialisation
    }

    @Override
    public List<ICandidate> getCandidates() throws RemoteException {
        return adminApp.getCandidates();
    }

}
